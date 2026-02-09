package com.rabbah.clw.presentation.offers

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.rabbah.clw.R
import com.rabbah.clw.presentation.utils.UiState
import com.rabbah.domain.model.network.response.OfferDto

@Composable
fun OffersScreen(offersViewModel: OffersViewModel) {

    val activeOffersUiState = offersViewModel.activeOffers.collectAsState()
    val expiredOffersUiState = offersViewModel.expiredOffers.collectAsState()

    OffersScreenContent(
        activeOffersUiState = activeOffersUiState.value,
        expiredOffersUiState = expiredOffersUiState.value,
        onActiveOffers = {
            offersViewModel.getActiveOffers(123)
        },
        onExpiredOffers = {
            offersViewModel.getExpiredOffers(123)
        },
        onActiveOffersRetry = {
            offersViewModel.getActiveOffers(123)
        },
        onExpiredOffersRetry = {
            offersViewModel.getExpiredOffers(123)
        }
    )
}

@Composable
private fun OffersScreenContent(
    activeOffersUiState: UiState<List<OfferDto>>,
    expiredOffersUiState: UiState<List<OfferDto>>,
    onActiveOffers: () -> Unit,
    onExpiredOffers: () -> Unit,
    onActiveOffersRetry: () -> Unit,
    onExpiredOffersRetry: () -> Unit
) {

    LaunchedEffect(Unit) {
        onActiveOffers.invoke()
    }

    LaunchedEffect(Unit) {
        onExpiredOffers.invoke()
    }

    var selectedTab by remember { mutableStateOf("Active") }
    val tabs = listOf("Active", "Expired")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FB)) // Light background
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Offers",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Custom Tab Row
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .border(1.dp, Color(0xFFEEEEEE), RoundedCornerShape(24.dp))
                .clip(RoundedCornerShape(24.dp))
                .background(Color.White)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                tabs.forEach { tab ->
                    val isSelected = selectedTab == tab
                    val count = if (tab == "Active" && activeOffersUiState is UiState.Success) {
                        activeOffersUiState.data.size
                    } else {
                        null
                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                            .padding(4.dp)
                            .clickable { selectedTab = tab },
                        contentAlignment = Alignment.Center
                    ) {
                        if (isSelected) {
                            Card(
                                modifier = Modifier.fillMaxSize(),
                                shape = RoundedCornerShape(20.dp),
                                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp), // Increased elevation for shadow
                                colors = CardDefaults.cardColors(containerColor = Color.White)
                            ) {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text(
                                            text = tab,
                                            color = Color(0xFF6750A4), // Purple
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 14.sp
                                        )
                                        if (count != null) {
                                            Spacer(modifier = Modifier.width(8.dp))
                                            Box(
                                                modifier = Modifier
                                                    .border(1.dp, Color(0xFFEEEEEE), RoundedCornerShape(4.dp))
                                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                                            ) {
                                                Text(
                                                    text = count.toString(),
                                                    color = Color(0xFF6750A4),
                                                    fontSize = 12.sp,
                                                    fontWeight = FontWeight.Medium
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            Text(
                                text = tab,
                                color = Color.Gray,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (selectedTab) {
            "Active" -> {
                when (activeOffersUiState) {
                    UiState.Loading -> Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { CircularProgressIndicator() }
                    is UiState.Error -> {
                        if (LocalInspectionMode.current.not()) {
                            Toast.makeText(
                                LocalContext.current,
                                activeOffersUiState.message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        ErrorUi("Active Offers") {
                            onActiveOffersRetry.invoke()
                        }
                    }

                    is UiState.Success<List<OfferDto>> -> {
                        OffersList(offers = activeOffersUiState.data)
                    }

                    is UiState.Idle -> Unit
                }
            }

            "Expired" -> {
                when (expiredOffersUiState) {
                    UiState.Loading -> Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { CircularProgressIndicator() }
                    is UiState.Error -> {
                        if (LocalInspectionMode.current.not()) {
                            Toast.makeText(
                                LocalContext.current,
                                expiredOffersUiState.message,
                                Toast.LENGTH_SHORT
                            ).show()
                            ErrorUi("Expired Offers") {
                                onExpiredOffersRetry.invoke()
                            }
                        }
                    }

                    is UiState.Success<List<OfferDto>> -> {
                        OffersList(offers = expiredOffersUiState.data)
                    }

                    is UiState.Idle -> Unit
                }
            }
        }
    }
}

@Composable
private fun OffersList(offers: List<OfferDto>) {
    LazyColumn(
        state = rememberLazyListState(),
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(offers) { offer ->
            OfferItem(offer)
        }
    }
}

@Composable
fun OfferItem(offer: OfferDto) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF3F0FF)), // Light purple bg
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(Color.White, RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                 AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(offer.imageUrl)
                        .placeholder(R.drawable.ic_discount_home)
                        .error(R.drawable.ic_discount_home)
                        .build(),
                    contentDescription = "Discount",
                    modifier = Modifier.size(24.dp),
                    contentScale = ContentScale.Fit
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = offer.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = offer.description,
                    fontSize = 12.sp,
                    color = Color.Gray,
                    lineHeight = 16.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_clock), // Assuming you have a clock icon
                        contentDescription = "Valid until",
                        tint = Color.Gray,
                        modifier = Modifier.size(12.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "valid until ${offer.expiry}",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

@Composable
private fun ErrorUi(offerTitle: String, onRetry: (() -> Unit)) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("$offerTitle couldn't be loaded, retry?")
        Button(
            modifier = Modifier
                .padding(top = 16.dp)
                .widthIn(min = 100.dp),
            shape = RoundedCornerShape(8.dp), onClick = {
                onRetry.invoke()
            }) {
            Text("Retry")
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOfferItem() {
    OfferItem(
        OfferDto(
            1,
            "",
            "Cashback 50%",
            "On your next purchase at any vending machine",
            "1 May 2025"
        )
    )
}
