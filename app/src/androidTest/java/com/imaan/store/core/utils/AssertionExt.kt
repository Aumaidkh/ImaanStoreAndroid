package com.imaan.store.core.utils

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput

fun ComposeContentTestRule.elementWithTagIsDisplayed(tag: String){
    onNodeWithTag(tag)
        .assertIsDisplayed()
}

fun ComposeContentTestRule.buttonIsDisabled(tag: String){
    onNodeWithTag(tag)
        .assertIsNotEnabled()
}

fun ComposeContentTestRule.buttonIsEnabled(tag: String){
    onNodeWithTag(tag)
        .assertIsEnabled()
}

fun ComposeContentTestRule.performClick(tag: String){
    onNodeWithTag(tag)
        .performClick()
}

fun ComposeContentTestRule.inputTextIn(tag: String,text: String){
    onNodeWithTag(tag)
        .performTextInput(text)
}

fun ComposeContentTestRule.screenOpened(tag: String){
    onNodeWithTag(tag)
        .assertIsDisplayed()
}