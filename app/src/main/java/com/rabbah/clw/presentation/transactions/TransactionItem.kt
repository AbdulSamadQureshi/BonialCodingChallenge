package com.rabbah.clw.presentation.transactions

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.rabbah.clw.R
import com.rabbah.clw.presentation.theme.CloseLoopWalletTheme
import com.rabbah.domain.model.network.response.ProductDto
import com.rabbah.domain.model.network.response.TransactionDto
import com.rabbah.domain.model.network.response.VendDto

@Composable
fun TransactionItem(transaction: TransactionDto, onItemClick:(transactionDto: TransactionDto) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize()
            .padding(bottom = 5.dp)
            .clickable {
                onItemClick(transaction)
            },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .size(48)
                    .data(transaction.purchasedItems?.firstOrNull()?.image)
                    .placeholder(R.drawable.placeholder_profile)
                    .error(R.drawable.placeholder_error)
                    .build(),
                contentDescription = "Discount",
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .border(1.dp, Color(0XFFEAEBED), RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = transaction.purchasedItems?.joinToString { it.title } ?: "",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = "${transaction.vendDto.title} • ${transaction.date}",
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            }
            Text(
                text = "-${transaction.grandTotal}",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TransactionItemPreview() {
    CloseLoopWalletTheme {
        TransactionItem(
            transaction = TransactionDto(
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
                    image = "https://via.placeholder.com/300x300?text=Potato+Chips",
                    available = true
                ),
            ),
            onItemClick = { },
        )
    }
}
