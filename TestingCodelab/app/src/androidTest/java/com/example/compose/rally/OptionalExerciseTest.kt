package com.example.compose.rally

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class OptionalExerciseTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            RallyApp()
        }
    }

    @Test
    fun rallyNavHost_navigateToAccounts_viaUI() {
        composeTestRule
            .onNodeWithContentDescription(RallyScreen.Accounts.name)
            .performClick()
            .assertIsSelected()
        composeTestRule
            .onNode(
                hasText(RallyScreen.Accounts.name.uppercase()),
                useUnmergedTree = true
            )
            .assertIsDisplayed()
    }

    @Test
    fun rallyNavHost_navigateToOverview_viaUI() {
        composeTestRule
            .onNodeWithContentDescription(RallyScreen.Overview.name)
            .performClick()
            .assertIsSelected()
        composeTestRule
            .onNode(
                hasText(RallyScreen.Overview.name.uppercase()),
                useUnmergedTree = true
            )
            .assertIsDisplayed()
    }

    @Test
    fun rallyNavHost_navigateToBills_viaUI() {
        composeTestRule
            .onNodeWithContentDescription(RallyScreen.Bills.name)
            .performClick()
            .assertIsSelected()
        composeTestRule
            .onNode(
                hasText(RallyScreen.Bills.name.uppercase()),
                useUnmergedTree = true
            )
            .assertIsDisplayed()
    }
}
