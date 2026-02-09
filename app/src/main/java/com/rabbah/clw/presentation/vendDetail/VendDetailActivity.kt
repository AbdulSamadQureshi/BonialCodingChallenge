package com.rabbah.clw.presentation.vendDetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.rabbah.clw.presentation.theme.CloseLoopWalletTheme

class VendDetailActivity : ComponentActivity() {

    private val vendId: Int by lazy {
        intent.getIntExtra(EXTRA_VEND_ID, -1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CloseLoopWalletTheme {
                // In a real app, you would use a ViewModel and pass vendId to it.
                VendDetailScreen(vendId = vendId)
            }
        }
    }

    companion object {
        private const val EXTRA_VEND_ID = "extra_vend_id"
        fun createIntent(context: Context, vendId: Int): Intent {
            return Intent(context, VendDetailActivity::class.java).apply {
                putExtra(EXTRA_VEND_ID, vendId)
            }
        }
    }
}
