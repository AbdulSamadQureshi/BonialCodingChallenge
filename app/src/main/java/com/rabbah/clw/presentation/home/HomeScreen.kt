package com.rabbah.clw.presentation.home

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.rabbah.clw.R
import com.rabbah.clw.presentation.nearbyVend.NearbyVendActivity
import com.rabbah.clw.presentation.theme.ColorPrimary
import com.rabbah.clw.presentation.theme.InviteFriend
import com.rabbah.clw.presentation.theme.White
import com.rabbah.clw.presentation.transactionDetail.TransactionDetailActivity
import com.rabbah.clw.presentation.utils.UiState
import com.rabbah.domain.model.network.response.BrochureDto

@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {

    val walletUiState by homeViewModel.walletUiState.collectAsState()
    val context = LocalContext.current
    val offerUiState by homeViewModel.offerUiState.collectAsState()
    val nearbyVendsUiState by homeViewModel.nearbyVendsUiState.collectAsState()
    val user by homeViewModel.user.collectAsState()

    LaunchedEffect(Unit) {
        homeViewModel.getWallet(123)
        homeViewModel.getHomeOffer(123)
        // Hardcoded location for now as per requirement context, usually this comes from LocationManager
        homeViewModel.getNearbyVends(24.7136, 46.6753)
    }

    LaunchedEffect(walletUiState) {
        if (walletUiState is UiState.Error) {
            Toast.makeText(context, (walletUiState as UiState.Error).message, Toast.LENGTH_SHORT).show()
        }
    }

    HomeContent(
        walletUiState = walletUiState,
        offerUiState = offerUiState,
        nearbyVendsUiState = nearbyVendsUiState,
        user = user,
        onTransactionsClick = {
            context.startActivity(Intent(context, NearbyVendActivity::class.java))
        },
        onTransactionItemClick = { transactionId ->
            context.startActivity(TransactionDetailActivity.createIntent(context, transactionId))
        }
    )
}

@Composable
fun HomeContent(
    walletUiState: UiState<WalletDto>,
    offerUiState: UiState<List<BrochureDto>>,
    nearbyVendsUiState: UiState<List<VendDto>>,
    user: UserDto?,
    onTransactionsClick: () -> Unit,
    onTransactionItemClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Header: Welcome message
            Text(
                text = stringResource(R.string.welcome_back_user, user?.name ?: ""),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            // Wallet Card
            WalletCard(walletUiState)

            Spacer(modifier = Modifier.height(24.dp))

            if (offerUiState is UiState.Success && (offerUiState as UiState.Success<List<BrochureDto>>).data.isNotEmpty()) {
                // Active Promotions
                Text(
                    text = stringResource(R.string.active_promotions),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                PromotionCard(offerUiState)

                Spacer(modifier = Modifier.height(16.dp))
            }

            // Invite Friend Banner
            InviteFriendBanner()

            Spacer(modifier = Modifier.height(24.dp))

            // Nearby Vending Machines
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Nearby Vending Machines",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "View All",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = ColorPrimary,
                    modifier = Modifier.clickable { onTransactionsClick() }
                )
            }
            
            Spacer(modifier = Modifier.height(12.dp))

            when (nearbyVendsUiState) {
                is UiState.Success -> {
                    val vends = nearbyVendsUiState.data
                    if (vends.isEmpty()) {
                        Text(stringResource(R.string.no_vending_machines_found_nearby), color = Color.Gray, fontSize = 14.sp)
                    } else {
                        // Fixed height for nested scrolling
                        Box(modifier = Modifier.height(300.dp)) {
                            LazyColumn {
                                items(vends) { vend ->
                                    VendingMachineItem(
                                        name = vend.title ?: "Vending Machine #${vend.id}",
                                        location = vend.address ?: "${vend.latitude}, ${vend.longitude}",
                                        distance = vend.distance ?: "Nearby",
                                        status = if (vend.available) "Available" else "Busy",
                                        imageUrl = vend.image
                                    )
                                }
                            }
                        }
                    }
                }
                is UiState.Loading -> {
                     CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                }
                is UiState.Error -> {
                    Text(text = nearbyVendsUiState.message ?: "Failed to load nearby machines", color = Color.Red, fontSize = 14.sp)
                }
                else -> Unit
            }
        }
    }
}

@Composable
fun WalletCard(walletUiState: UiState<WalletDto>) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.bg_wallet_login), // Placeholder icon
                    contentDescription = "Wallet Icon",
                    modifier = Modifier
                        .size(53.dp)
                        .background(Color(0xFFECEFF1), RoundedCornerShape(12.dp))
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = "Current Balance",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                    when (walletUiState) {
                        is UiState.Success -> {
                            Text(
                                text = "SAR ${walletUiState.data.walletBalance ?: 0.0}",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        }
                        is UiState.Loading -> {
                            CircularProgressIndicator(modifier = Modifier.size(20.dp), strokeWidth = 2.dp)
                        }
                        else -> {
                            Text(
                                text = "SAR --.--",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        }
                    }
                }
            }
            
            IconButton(
                onClick = { /* Add Money Action */ },
                modifier = Modifier
                    .size(48.dp)
                    .background(ColorPrimary, RoundedCornerShape(12.dp))
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Money",
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
fun PromotionCard(offerUiState: UiState<List<BrochureDto>>) {
    when (offerUiState) {
        is UiState.Success -> {
            val offers = offerUiState.data
            if (offers.isNotEmpty()) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    val pagerState = rememberPagerState(pageCount = { offers.size })
                    HorizontalPager(
                        state = pagerState,
                        modifier = Modifier.fillMaxWidth()
                    ) { page ->
                        val offer = offers[page]
                        PromotionItem(offer)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    // Indicator
                    Row(
                        Modifier
                            .wrapContentHeight()
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        repeat(pagerState.pageCount) { iteration ->
                            val color = if (pagerState.currentPage == iteration) ColorPrimary else Color.LightGray
                            val width = if (pagerState.currentPage == iteration) 12.dp else 5.dp
                            val height = 5.dp
                            Box(
                                modifier = Modifier
                                    .padding(2.dp)
                                    .clip(CircleShape)
                                    .background(color)
                                    .size(width = width, height = height)
                            )
                        }
                    }
                }
            }
        }
        is UiState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(92.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is UiState.Error -> {
            Toast.makeText(LocalContext.current, offerUiState.message, Toast.LENGTH_SHORT).show()
        }
        else -> Unit
    }
}

@Composable
fun PromotionItem(offer: BrochureDto) {
    val uriHandler = LocalUriHandler.current
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF3F0FF)), // Light purple bg
        modifier = Modifier
            .fillMaxWidth()
            .height(92.dp)
            .clickable {
                // Open webView with url. Assuming URL is generic for now as it's missing in OfferDto
                uriHandler.openUri(offer.imageUrl)
            }
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(offer.imageUrl)
                    .placeholder(R.drawable.ic_discount_home)
                    .error(R.drawable.ic_discount_home)
                    .build(),
                contentDescription = "Discount",
                modifier = Modifier.size(47.dp),
                contentScale = ContentScale.Fit
            )
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column {
                Text(
                    text = offer.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = offer.description,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = offer.expiry,
                    fontSize = 10.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun InviteFriendBanner() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Card(
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFD1E1FF)), // Light Blue
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.ic_gift_home), // Placeholder
                    contentDescription = "Gift",
                    modifier = Modifier
                        .padding(
                            start = 16.dp,
                            top = 16.dp,
                            end = 8.dp,
                            bottom = 8.dp
                        )
                        .size(40.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    modifier = Modifier.padding(end = 16.dp),
                    text = "Earn SAR 5 for each friend you invite.",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF184DBF)
                )
            }
        }

        Card(
            shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF2563EB).copy(alpha = 0.1f)),
            modifier = Modifier
                .height(40.dp)
                .fillMaxWidth()
                .clickable { }
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ) {
                Text(
                    text = stringResource(R.string.invite_friends),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = InviteFriend,
                )

                Icon(
                    modifier = Modifier.size(20.dp),
                    imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                    tint = Color(0XFF2563EB),
                    contentDescription = "Invite",
                )
            }

        }

    }
}

@Composable
fun VendingMachineItem(name: String, location: String, distance: String, status: String, imageUrl: String? = null) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .placeholder(R.drawable.placeholder_profile)
                    .error(R.drawable.placeholder_error)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.LightGray)
            )
            
            Spacer(modifier = Modifier.width(12.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = name,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    
                    Text(
                        text = status,
                        fontSize = 12.sp,
                        color = if (status == "Available") Color(0xFF4CAF50) else Color(0xFFFF9800),
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .background(
                                if (status == "Available") Color(0xFFE8F5E9) else Color(0xFFFFF3E0),
                                RoundedCornerShape(4.dp)
                            )
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    )
                }
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Row {
                    Text(text = location, fontSize = 12.sp, color = Color.Gray)
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(text = distance, fontSize = 12.sp, color = Color.Gray)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeContentPreview() {
    HomeContent(
        walletUiState = UiState.Success(WalletDto(walletBalance = 122.75)),
        offerUiState = UiState.Success(
            data = listOf(
                BrochureDto(1, "Title 1", "Description 1", "Expiry 1", "20 day"),
                BrochureDto(2, "Title 2", "Description 2", "Expiry 2", "30 day")
            )
        ),
        nearbyVendsUiState = UiState.Success(listOf(VendDto(1, 0.0, 0.0, "Title", "Address", "50m", "", true))),
        user = UserDto(name = "John Doe"),
        onTransactionsClick = {},
        onTransactionItemClick = {}
    )
}
