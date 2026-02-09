package com.rabbah.clw.presentation.transactions

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.rabbah.clw.presentation.theme.CloseLoopWalletTheme
import org.koin.androidx.compose.koinViewModel

class TransactionsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CloseLoopWalletTheme {
                val transactionsViewModel: TransactionsViewModel = koinViewModel()
                TransactionsScreen(transactionsViewModel)
            }
        }
    }
}


