package com.bonial.brochure.presentation.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.bonial.brochure.presentation.navigation.BrochureNavGraph
import com.bonial.brochure.presentation.theme.CloseLoopWalletTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BrochuresActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CloseLoopWalletTheme {
                val navController = rememberNavController()
                BrochureNavGraph(navController = navController)
            }
        }
    }
}
