package com.rabbah.clw.presentation.transactions

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rabbah.clw.R
import com.rabbah.clw.presentation.theme.CloseLoopWalletTheme
import com.rabbah.clw.presentation.transactionDetail.TransactionDetailActivity
import com.rabbah.clw.presentation.utils.UiState
import com.rabbah.domain.model.network.response.ProductDto
import com.rabbah.domain.model.network.response.TransactionDto
import com.rabbah.domain.model.network.response.VendDto


@Composable
fun TransactionsScreen(viewModel: TransactionsViewModel) {
    val transactionsState = viewModel.transactionsUiState.collectAsState()
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.getTransactions()
    }
    TransactionsScreenContent(
        transactionsUiState = transactionsState.value,
        onItemClick = {
            context.startActivity(TransactionDetailActivity.createIntent(context, it.id))
        }
    )

}

@Composable
fun TransactionsScreenContent(
    transactionsUiState: UiState<List<TransactionDto>>,
    onItemClick: (TransactionDto) -> Unit
) {
    Column(
        modifier = Modifier
            .background(Color(0XFFE5E7EB))
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Transactions",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 16.dp)
        )

        val showViewAll = remember { mutableStateOf(false) }


        when (transactionsUiState) {
            is UiState.Loading -> {
                showViewAll.value = false
                CircularProgressIndicator()
            }

            is UiState.Error -> {
                showViewAll.value = false
                NoTransactionsUi()
            }

            is UiState.Success -> {
                val transactions = transactionsUiState.data
                if (transactions.isEmpty()) {
                    NoTransactionsUi()
                } else {
                    showViewAll.value = true
                    TransactionsList(
                        transactions = transactions,
                        onItemClick = onItemClick
                    )
                }
            }

            else -> {
                showViewAll.value = false
                NoTransactionsUi()
            }
        }
    }
}

@Composable
fun NoTransactionsUi() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp)
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .background(Color(0xFFF3F0FF), RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_no_transactions), // Replace with appropriate icon
                contentDescription = null,
            )
        }
        Spacer(modifier = Modifier.height(34.dp))
        Text(
            fontSize = 18.sp,
            text = "No Transaction",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1B132A)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            fontSize = 14.sp,
            text = "You Have no transaction data at this moment",
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFF64758B),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun TransactionsList(
    transactions: List<TransactionDto>,
    onItemClick: (transactionDto: TransactionDto) -> Unit
) {
    LazyColumn(
        modifier = Modifier.wrapContentSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(transactions) { transaction ->
            TransactionItem(transaction = transaction, onItemClick = {
                onItemClick(it)
            })
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TransactionScreenPreview() {
    CloseLoopWalletTheme {

        val mockTransactions = listOf(
            TransactionDto(
                id = 1,
                purchasedItems = listOf(
                    ProductDto(
                        id = 123,
                        title = "chips",
                        quantity = 1,
                        unitPrice = 5.00,
                        image = null
                    )
                ),
                status = true,
                date = "10-10-2025",
                grandTotal = 5.00,
                vendDto = VendDto(
                    id = 123,
                    latitude = 123.0,
                    longitude = 123.0,
                    title = "Central Library",
                    address = "temp address",
                    distance = "5 km away",
                    image = null,
                    available = true
                ),
            ),
            TransactionDto(
                id = 1,
                purchasedItems = listOf(
                    ProductDto(
                        id = 123,
                        title = "Lays",
                        quantity = 1,
                        unitPrice = 15.00,
                        image = null
                    )
                ),
                status = true,
                date = "15-15-2025",
                grandTotal = 10.00,
                vendDto = VendDto(
                    id = 123,
                    latitude = 123.0,
                    longitude = 123.0,
                    title = "Central Library",
                    address = "temp address",
                    distance = "5 km away",
                    image = null,
                    available = true
                ),
            )
        )

        TransactionsScreenContent(
            transactionsUiState = UiState.Success(mockTransactions),
            onItemClick = {}
        )
    }
}
