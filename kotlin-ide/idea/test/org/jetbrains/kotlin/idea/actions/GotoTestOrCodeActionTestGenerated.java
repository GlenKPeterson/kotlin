/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.actions;

import com.intellij.testFramework.TestDataPath;
import org.jetbrains.kotlin.test.JUnit3RunnerWithInners;
import org.jetbrains.kotlin.test.KotlinTestUtils;
import org.jetbrains.kotlin.test.TestMetadata;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.regex.Pattern;

/** This class is generated by {@link org.jetbrains.kotlin.generators.tests.TestsPackage}. DO NOT MODIFY MANUALLY */
@SuppressWarnings("all")
@TestMetadata("kotlin/idea/testData/navigation/gotoTestOrCode")
@TestDataPath("$PROJECT_ROOT")
@RunWith(JUnit3RunnerWithInners.class)
public class GotoTestOrCodeActionTestGenerated extends AbstractGotoTestOrCodeActionTest {
    private void runTest(String testDataFilePath) throws Exception {
        KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
    }

    public void testAllFilesPresentInGotoTestOrCode() throws Exception {
        KotlinTestUtils.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("kotlin/idea/testData/navigation/gotoTestOrCode"), Pattern.compile("^(.+)\\.main\\..+$"), null, true);
    }

    @TestMetadata("fromJavaClassToTest.main.java")
    public void testFromJavaClassToTest() throws Exception {
        runTest("kotlin/idea/testData/navigation/gotoTestOrCode/fromJavaClassToTest.main.java");
    }

    @TestMetadata("fromJavaTestToKotlinClass.main.java")
    public void testFromJavaTestToKotlinClass() throws Exception {
        runTest("kotlin/idea/testData/navigation/gotoTestOrCode/fromJavaTestToKotlinClass.main.java");
    }

    @TestMetadata("fromJavaTestToKotlinFile.main.java")
    public void testFromJavaTestToKotlinFile() throws Exception {
        runTest("kotlin/idea/testData/navigation/gotoTestOrCode/fromJavaTestToKotlinFile.main.java");
    }

    @TestMetadata("fromKotlinClassToTest.main.kt")
    public void testFromKotlinClassToTest() throws Exception {
        runTest("kotlin/idea/testData/navigation/gotoTestOrCode/fromKotlinClassToTest.main.kt");
    }

    @TestMetadata("fromKotlinFileToTest.main.kt")
    public void testFromKotlinFileToTest() throws Exception {
        runTest("kotlin/idea/testData/navigation/gotoTestOrCode/fromKotlinFileToTest.main.kt");
    }

    @TestMetadata("fromKotlinTestToJavaClass.main.kt")
    public void testFromKotlinTestToJavaClass() throws Exception {
        runTest("kotlin/idea/testData/navigation/gotoTestOrCode/fromKotlinTestToJavaClass.main.kt");
    }

    @TestMetadata("fromKotlinTestToKotlinClass.main.kt")
    public void testFromKotlinTestToKotlinClass() throws Exception {
        runTest("kotlin/idea/testData/navigation/gotoTestOrCode/fromKotlinTestToKotlinClass.main.kt");
    }

    @TestMetadata("fromKotlinTestToKotlinFile.main.kt")
    public void testFromKotlinTestToKotlinFile() throws Exception {
        runTest("kotlin/idea/testData/navigation/gotoTestOrCode/fromKotlinTestToKotlinFile.main.kt");
    }
}
