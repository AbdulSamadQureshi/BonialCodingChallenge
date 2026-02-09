package com.rabbah.clw.presentation.transactionDetail

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rabbah.clw.presentation.utils.UiState
import com.rabbah.domain.model.network.response.ProductDto
import com.rabbah.domain.model.network.response.TransactionDetailDto


@Composable
fun TransactionDetailScreen(
    transactionId: Int,
    viewModel: TransactionDetailViewModel,
) {

    val uiState by viewModel.transactionDetailUiState.collectAsState()

    TransactionDetailContent(
        uiState,
        onGetTransactionDetail = {
            viewModel.getTransactionDetail(transactionId, 123)
        })

}

@Composable
private fun TransactionDetailContent(
    uiState: UiState<TransactionDetailDto>,
    onGetTransactionDetail: () -> Unit
) {


    LaunchedEffect(Unit) {
        onGetTransactionDetail.invoke()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (uiState) {
            is UiState.Success<TransactionDetailDto> -> {
                TransactionDetailUi(uiState.data)
            }

            is UiState.Loading -> {
                CircularProgressIndicator()
            }

            is UiState.Error -> {
                if(LocalInspectionMode.current.not()) {
                    Toast.makeText(LocalContext.current, uiState.message, Toast.LENGTH_SHORT)
                        .show()
                }
                ErrorUi(onRetry = onGetTransactionDetail)
            }

            else -> Unit

        }
    }
}

@Composable
private fun ErrorUi(onRetry: (() -> Unit)? = null) {
    Column(
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Transaction Detail couldn't be loaded, retry?")
        Button(
            modifier = Modifier
                .padding(top = 16.dp)
                .widthIn(min = 100.dp),
            shape = RoundedCornerShape(8.dp), onClick = {
                onRetry?.invoke()
            }) {
            Text("Retry")
        }

    }
}

@Composable
private fun TransactionDetailUi(transactionDetailDto: TransactionDetailDto) {
    Text(
        modifier = Modifier.padding(top = 16.dp, bottom = 50.dp),
        text = "Transaction Details",
        fontSize = 32.sp,
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {

        Text(
            text = "id: ${transactionDetailDto.id}",
            fontSize = 20.sp,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Grand Total ${transactionDetailDto.grandTotal}",
            fontSize = 20.sp,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Status: ${transactionDetailDto.status}",
            fontSize = 20.sp,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "date: ${transactionDetailDto.date}",
            fontSize = 20.sp,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Purchased Items: ",
                fontSize = 20.sp,
            )
            Text(
                text = transactionDetailDto.purchasedItems.joinToString { it.title },
                fontSize = 15.sp,
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    val transactionDetailDto = TransactionDetailDto(
        id = 123,
        status = true,
        date = "12-12-2025",
        grandTotal = 55.5,
        purchasedItems = listOf(
            ProductDto(
                id = 123,
                title = "Product 1",
                quantity = 2,
                unitPrice = 15.2,
                image = "https://images.unsplash.com/photo-1607082349811-94d1a7a2c11c?auto=format&fit=crop&w=800&q=80"
            ),
            ProductDto(
                id = 446,
                title = "Product 2",
                quantity = 3,
                unitPrice = 15.2,
                image = "https://images.unsplash.com/photo-1607082349811-94d1a7a2c11c?auto=format&fit=crop&w=800&q=80"
            ),
        )
    )
    TransactionDetailContent(UiState.Success(transactionDetailDto),{})
}
