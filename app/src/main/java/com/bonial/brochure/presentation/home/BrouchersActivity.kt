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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bonial.brochure.presentation.theme.CloseLoopWalletTheme
import org.koin.androidx.compose.koinViewModel

class BrouchersActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CloseLoopWalletTheme {
                val brochuresViewModel: BrochuresViewModel = koinViewModel()
                BrochuresUi(brochuresViewModel)
            }
        }
    }
}



@Composable
fun BrouchersActivityContent(
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
fun BrouchersActivityPreview() {
    CloseLoopWalletTheme {
        BrouchersActivityContent(
            screenContent = {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    HomeContentPreview()
                }
            }
        )
    }
}
