package com.rabbah.clw.presentation.signup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.rabbah.clw.presentation.theme.CloseLoopWalletTheme

class CompleteProfileActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CloseLoopWalletTheme {
                CompleteProfileScreen()
            }
        }
    }
}
