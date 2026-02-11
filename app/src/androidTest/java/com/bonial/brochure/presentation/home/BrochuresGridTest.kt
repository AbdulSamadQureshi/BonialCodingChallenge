package com.bonial.brochure.presentation.home

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bonial.brochure.presentation.theme.CloseLoopWalletTheme
import com.bonial.domain.model.network.response.BrochureDto
import com.bonial.domain.model.network.response.ContentWrapperDto
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BrochuresGridTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    /**
     * Verifies that premium brochure items has twice the width than the simple ones
     */
    @Test
    fun brochureGrid_itemWidths() {
        val mockData = listOf(
            ContentWrapperDto(
                contentType = "brochure",
                content = listOf(BrochureDto(title = "Simple Brochure"))
            ),
            ContentWrapperDto(
                contentType = "brochurePremium",
                content = listOf(BrochureDto(title = "Premium Brochure"))
            )
        )

        composeTestRule.setContent {
            CloseLoopWalletTheme {
                BrochuresGrid(contents = mockData)
            }
        }

        // Get the widths of the simple and premium items
        val simpleItemWidth = composeTestRule.onNodeWithTag("brochure_item_brochure").fetchSemanticsNode().size.width
        val premiumItemWidth = composeTestRule.onNodeWithTag("brochure_item_brochurePremium").fetchSemanticsNode().size.width

        // Assert that the premium item is significantly wider than the simple one
        // A simple ratio check confirms the span difference (e.g., premium is at least 1.5x wider)
        assert(premiumItemWidth > simpleItemWidth * 1.5f)
    }
}
