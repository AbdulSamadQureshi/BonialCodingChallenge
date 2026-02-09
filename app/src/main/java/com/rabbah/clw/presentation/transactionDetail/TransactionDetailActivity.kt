package com.rabbah.clw.presentation.transactionDetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.rabbah.clw.presentation.login.LoginViewModel
import com.rabbah.clw.presentation.theme.CloseLoopWalletTheme
import org.koin.androidx.compose.koinViewModel

class TransactionDetailActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: TransactionDetailViewModel = koinViewModel()
            CloseLoopWalletTheme {
                intent.extras?.getInt(KEY_TRANSACTION_ID)?.let {
                    TransactionDetailScreen(it, viewModel)
                }

            }
        }
    }

    companion object KEY {
        const val KEY_TRANSACTION_ID = "KEY_TRANSACTION_ID"
        fun createIntent(context: Context, transactionId: Int): Intent {
            val bundle = Bundle().apply {
                putInt(KEY_TRANSACTION_ID, transactionId)
            }
            return Intent(context, TransactionDetailActivity::class.java).apply {
                putExtras(bundle)
            }
        }
    }

}