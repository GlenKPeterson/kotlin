/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.analysis.low.level.api.fir.sessions

import org.jetbrains.kotlin.analyzer.ModuleSourceInfoBase
import java.util.concurrent.ConcurrentHashMap

@JvmInline
internal value class LibrariesCache(private val cache: ConcurrentHashMap<ModuleSourceInfoBase, FirIdeLibrariesSession> = ConcurrentHashMap()) {
    fun cached(moduleSourceInfo: ModuleSourceInfoBase, create: (ModuleSourceInfoBase) -> FirIdeLibrariesSession): FirIdeLibrariesSession =
        cache.computeIfAbsent(moduleSourceInfo, create)
}
