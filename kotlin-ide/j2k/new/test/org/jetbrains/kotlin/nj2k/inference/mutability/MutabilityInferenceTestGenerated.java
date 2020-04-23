/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.nj2k.inference.mutability;

import com.intellij.testFramework.TestDataPath;
import org.jetbrains.kotlin.test.JUnit3RunnerWithInners;
import org.jetbrains.kotlin.test.KotlinTestUtils;
import org.jetbrains.kotlin.test.TestMetadata;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.regex.Pattern;

/** This class is generated by {@link org.jetbrains.kotlin.generators.tests.TestsPackage}. DO NOT MODIFY MANUALLY */
@SuppressWarnings("all")
@TestMetadata("kotlin/j2k/new/testData/inference/mutability")
@TestDataPath("$PROJECT_ROOT")
@RunWith(JUnit3RunnerWithInners.class)
public class MutabilityInferenceTestGenerated extends AbstractMutabilityInferenceTest {
    private void runTest(String testDataFilePath) throws Exception {
        KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
    }

    public void testAllFilesPresentInMutability() throws Exception {
        KotlinTestUtils.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("kotlin/j2k/new/testData/inference/mutability"), Pattern.compile("^(.+)\\.kt$"), null, true);
    }

    @TestMetadata("arrayList.kt")
    public void testArrayList() throws Exception {
        runTest("kotlin/j2k/new/testData/inference/mutability/arrayList.kt");
    }

    @TestMetadata("collectionMutableCalls.kt")
    public void testCollectionMutableCalls() throws Exception {
        runTest("kotlin/j2k/new/testData/inference/mutability/collectionMutableCalls.kt");
    }

    @TestMetadata("covariance.kt")
    public void testCovariance() throws Exception {
        runTest("kotlin/j2k/new/testData/inference/mutability/covariance.kt");
    }

    @TestMetadata("iteratorCall.kt")
    public void testIteratorCall() throws Exception {
        runTest("kotlin/j2k/new/testData/inference/mutability/iteratorCall.kt");
    }

    @TestMetadata("IteratorMutableCalls.kt")
    public void testIteratorMutableCalls() throws Exception {
        runTest("kotlin/j2k/new/testData/inference/mutability/IteratorMutableCalls.kt");
    }

    @TestMetadata("list.kt")
    public void testList() throws Exception {
        runTest("kotlin/j2k/new/testData/inference/mutability/list.kt");
    }

    @TestMetadata("listIteratorMutableCalls.kt")
    public void testListIteratorMutableCalls() throws Exception {
        runTest("kotlin/j2k/new/testData/inference/mutability/listIteratorMutableCalls.kt");
    }

    @TestMetadata("listMutableCalls.kt")
    public void testListMutableCalls() throws Exception {
        runTest("kotlin/j2k/new/testData/inference/mutability/listMutableCalls.kt");
    }

    @TestMetadata("listOfListsForEach.kt")
    public void testListOfListsForEach() throws Exception {
        runTest("kotlin/j2k/new/testData/inference/mutability/listOfListsForEach.kt");
    }

    @TestMetadata("listOfMutableList.kt")
    public void testListOfMutableList() throws Exception {
        runTest("kotlin/j2k/new/testData/inference/mutability/listOfMutableList.kt");
    }

    @TestMetadata("mapEntryMutableCalls.kt")
    public void testMapEntryMutableCalls() throws Exception {
        runTest("kotlin/j2k/new/testData/inference/mutability/mapEntryMutableCalls.kt");
    }

    @TestMetadata("mapMutableCalls.kt")
    public void testMapMutableCalls() throws Exception {
        runTest("kotlin/j2k/new/testData/inference/mutability/mapMutableCalls.kt");
    }

    @TestMetadata("setMutableCalls.kt")
    public void testSetMutableCalls() throws Exception {
        runTest("kotlin/j2k/new/testData/inference/mutability/setMutableCalls.kt");
    }
}
