/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.commonizer.mergedtree

import org.jetbrains.kotlin.commonizer.cir.CirClass
import org.jetbrains.kotlin.commonizer.cir.CirClassifier
import org.jetbrains.kotlin.commonizer.cir.CirEntityId
import org.jetbrains.kotlin.commonizer.cir.CirTypeAlias
import org.jetbrains.kotlin.commonizer.tree.*
import org.jetbrains.kotlin.commonizer.utils.compact
import org.jetbrains.kotlin.commonizer.utils.compactMapValues

fun CirClassifierIndex(tree: CirTreeRoot): CirClassifierIndex {
    return CirClassifierIndexBuilder().apply { invoke(tree) }.build()
}

interface CirClassifierIndex {
    val allClassifierIds: Set<CirEntityId>
    fun findClassifier(id: CirEntityId): CirClassifier?
    fun findTypeAliasesWithUnderlyingType(underlyingClassifier: CirEntityId): List<CirTreeTypeAlias>
}

/**
 * Resolves all typealias that will either declare the given [underlyingClassifier] as their underlying type directly, *or transitively*.
 * The returned List will be ordered BFS:
 * Elements at the beginning of the returned List will have a 'shorter path' to the underlying classifier
 */
internal fun CirClassifierIndex.findAllTypeAliasesWithUnderlyingType(underlyingClassifier: CirEntityId): List<CirTreeTypeAlias> {
    /* Fast Path (no aliases) */
    val firstAliases = findTypeAliasesWithUnderlyingType(underlyingClassifier)
    if (firstAliases.isEmpty()) return emptyList()

    val resolved = ArrayList<CirTreeTypeAlias>(firstAliases)
    val enqueued = ArrayDeque<CirEntityId>(firstAliases.size * 2)
    firstAliases.forEach { alias -> enqueued.add(alias.id) }

    while (enqueued.isNotEmpty()) {
        val next = enqueued.removeFirst()
        val aliases = findTypeAliasesWithUnderlyingType(next)
        resolved.addAll(aliases)
        aliases.forEach { alias -> enqueued.add(alias.id) }
    }

    return resolved
}

internal fun CirClassifierIndex.findClass(id: CirEntityId): CirClass? = findClassifier(id) as? CirClass

internal fun CirClassifierIndex.findTypeAlias(id: CirEntityId): CirTypeAlias? = findClassifier(id) as? CirTypeAlias

internal fun CirClassifierIndex.getClass(id: CirEntityId): CirClass =
    findClass(id) ?: throw NoSuchElementException("Missing class $id")

internal fun CirClassifierIndex.getTypeAlias(id: CirEntityId): CirTypeAlias =
    findTypeAlias(id) ?: throw NoSuchElementException("Missing type alias $id")

internal fun CirClassifierIndex.getClassifier(id: CirEntityId): CirClassifier =
    findClassifier(id) ?: throw NoSuchElementException("Missing classifier $id")

private class CirClassifierIndexImpl(
    override val allClassifierIds: Set<CirEntityId>,
    private val classifiersById: Map<CirEntityId, CirClassifier>,
    private val typeAliasesByUnderlyingType: Map<CirEntityId, List<CirTreeTypeAlias>>
) : CirClassifierIndex {

    override fun findClassifier(id: CirEntityId): CirClassifier? {
        return classifiersById[id]
    }

    override fun findTypeAliasesWithUnderlyingType(underlyingClassifier: CirEntityId): List<CirTreeTypeAlias> {
        return typeAliasesByUnderlyingType[underlyingClassifier].orEmpty()
    }
}

private class CirClassifierIndexBuilder {
    private val typeAliasesByUnderlyingType = mutableMapOf<CirEntityId, MutableList<CirTreeTypeAlias>>()
    private val classifiersById = mutableMapOf<CirEntityId, CirClassifier>()
    private val classifierIds = mutableSetOf<CirEntityId>()

    operator fun invoke(tree: CirTreeRoot) {
        tree.modules.forEach { module -> this(module) }
    }

    operator fun invoke(module: CirTreeModule) {
        module.packages.forEach { pkg -> this(pkg) }
    }

    operator fun invoke(pkg: CirTreePackage) {
        pkg.typeAliases.forEach { typeAlias -> this(typeAlias) }
        pkg.classes.forEach { clazz -> this(clazz) }
    }

    operator fun invoke(typeAlias: CirTreeTypeAlias) {
        classifierIds.add(typeAlias.id)
        classifiersById[typeAlias.id] = typeAlias.typeAlias
        typeAliasesByUnderlyingType.getOrPut(typeAlias.typeAlias.underlyingType.classifierId) { mutableListOf() }.add(typeAlias)
    }

    operator fun invoke(clazz: CirTreeClass) {
        classifierIds.add(clazz.id)
        classifiersById[clazz.id] = clazz.clazz
        clazz.classes.forEach { innerClazz -> this(innerClazz) }
    }

    fun build(): CirClassifierIndex {
        return CirClassifierIndexImpl(
            allClassifierIds = classifierIds.toSet(),
            classifiersById = classifiersById.compact(),
            typeAliasesByUnderlyingType = typeAliasesByUnderlyingType.compactMapValues { (_, list) -> list.compact() }
        )
    }
}
