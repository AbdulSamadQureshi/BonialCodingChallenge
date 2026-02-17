package com.bonial.brochure.presentation.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bonial.brochure.presentation.model.BrochureUi
import com.bonial.brochure.presentation.theme.CloseLoopWalletTheme
import org.koin.androidx.compose.koinViewModel

class BrochuresActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CloseLoopWalletTheme {
                val brochuresViewModel: BrochuresViewModel = koinViewModel()
                BrochuresScreen(brochuresViewModel)
            }
        }
    }
}


@Composable
fun BrochuresActivityContent(
    screenContent: @Composable () -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding(),

        ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            screenContent()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun BrochuresActivityPreview() {
    CloseLoopWalletTheme {
        BrochuresActivityContent(
            screenContent = {
                val mockData = listOf(
                    BrochureUi(
                        id = 1L,
                        title = "Brochure 1",
                        publisherName = "Publisher 1",
                        coverUrl = null,
                        distance = 0.5
                    ),
                    BrochureUi(
                        id = 2L,
                        title = "Brochure 2",
                        publisherName = "Publisher 2",
                        coverUrl = null,
                        distance = 1.2
                    )
                )
                BrochuresGrid(brochures = mockData)
            }
        )
    }
}
