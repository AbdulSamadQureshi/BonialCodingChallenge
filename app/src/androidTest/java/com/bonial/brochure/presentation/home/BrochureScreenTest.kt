package com.bonial.brochure.presentation.home

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BrochureScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<BrochuresActivity>()

    /**
     * Checks if the screen is displayed with the grid
     */
    @Test
    fun brochureGrid_isDisplayed() {
        // Wait for the grid to appear, which confirms data loading and successful UI state
        composeTestRule.waitUntil(timeoutMillis = 10000) {
            composeTestRule
                .onAllNodesWithTag("brochures_grid")
                .fetchSemanticsNodes().isNotEmpty()
        }
        composeTestRule.onNodeWithTag("brochures_grid").assertIsDisplayed()
    }
}
