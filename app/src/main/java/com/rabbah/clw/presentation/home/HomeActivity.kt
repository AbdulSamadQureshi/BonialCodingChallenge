package com.rabbah.clw.presentation.home

import android.content.Intent
import android.nfc.NfcAdapter
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rabbah.clw.R
import com.rabbah.clw.presentation.accountDetail.AccountDetailViewModel
import com.rabbah.clw.presentation.more.ProfileScreen
import com.rabbah.clw.presentation.nfc.NfcBottomSheet
import com.rabbah.clw.presentation.offers.OffersScreen
import com.rabbah.clw.presentation.offers.OffersViewModel
import com.rabbah.clw.presentation.theme.CloseLoopWalletTheme
import com.rabbah.clw.presentation.theme.ColorPrimary
import com.rabbah.clw.presentation.theme.White
import com.rabbah.clw.presentation.utils.UiState
import com.rabbah.clw.presentation.wallet.WalletScreen
import com.rabbah.clw.presentation.wallet.WalletViewModel
import org.koin.androidx.compose.koinViewModel

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CloseLoopWalletTheme {
                val homeViewModel: HomeViewModel = koinViewModel()
                val walletViewModel: WalletViewModel = koinViewModel()
                val offersViewModel: OffersViewModel = koinViewModel()
                val accountDetailViewModel: AccountDetailViewModel = koinViewModel()
                HomeUi(homeViewModel, walletViewModel, offersViewModel, accountDetailViewModel)
            }
        }
    }
}

@Composable
private fun HomeUi(
    homeViewModel: HomeViewModel,
    walletViewModel: WalletViewModel,
    offersViewModel: OffersViewModel,
    accountDetailViewModel: AccountDetailViewModel,
) {
    val context = LocalContext.current
    val walletUiState by walletViewModel.walletUiState.collectAsState()
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.HOME) }
    var showNfcBottomSheet by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        walletViewModel.getWallet(123)
    }

    val cardData = (walletUiState as? UiState.Success)?.data?.card?.number

    if (showNfcBottomSheet && cardData != null) {
        NfcBottomSheet(
            cardData = cardData,
            onDismissRequest = { showNfcBottomSheet = false }
        )
    }

    HomeActivityContent(
        currentDestination = currentDestination,
        onNavigate = {
            if (it == AppDestinations.Scan) {
                if (cardData != null) {
                    val nfcAdapter = NfcAdapter.getDefaultAdapter(context)
                    if (nfcAdapter != null && nfcAdapter.isEnabled) {
                        showNfcBottomSheet = true
                    } else {
                        Toast.makeText(context, "NFC is not enabled", Toast.LENGTH_SHORT).show()
                        val intent = Intent(Settings.ACTION_NFC_SETTINGS)
                        if (intent.resolveActivity(context.packageManager) != null) {
                            context.startActivity(intent)
                        }
                    }
                } else {
                    Toast.makeText(context, "Card data not available", Toast.LENGTH_SHORT).show()
                }
            } else {
                currentDestination = it
            }
        },
        screenContent = {
            when (currentDestination) {
                AppDestinations.HOME -> HomeScreen(homeViewModel)
                AppDestinations.Wallet -> WalletScreen(walletViewModel = walletViewModel)
                AppDestinations.Scan -> { /* Scan Screen Placeholder */
                }

                AppDestinations.Offers -> OffersScreen(offersViewModel = offersViewModel)
                AppDestinations.Profile -> ProfileScreen()
            }
        }
    )
}

@Composable
fun HomeActivityContent(
    currentDestination: AppDestinations,
    onNavigate: (AppDestinations) -> Unit,
    screenContent: @Composable () -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding(),
        bottomBar = {
            CustomBottomNavigation(
                currentDestination = currentDestination,
                onNavigate = onNavigate
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            screenContent()
        }
    }
}

@Composable
fun CustomBottomNavigation(
    currentDestination: AppDestinations,
    onNavigate: (AppDestinations) -> Unit
) {
    Box(
        modifier = Modifier
            .background(White)
            .fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(R.drawable.bg_tab_bar),
            contentDescription = null
        )
        // Background Card
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Use a Row with weights to ensure even spacing
            BottomNavItem(Modifier.weight(1f), AppDestinations.HOME, currentDestination, onNavigate)
            BottomNavItem(
                Modifier.weight(1f),
                AppDestinations.Wallet,
                currentDestination,
                onNavigate
            )
            Spacer(modifier = Modifier.weight(1f)) // Spacer for the central FAB
            BottomNavItem(
                Modifier.weight(1f),
                AppDestinations.Offers,
                currentDestination,
                onNavigate
            )
            BottomNavItem(
                Modifier.weight(1f),
                AppDestinations.Profile,
                currentDestination,
                onNavigate
            )
        }

        // Center Floating Action Button (Scan)
        Column(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.TopCenter)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = { onNavigate(AppDestinations.Scan) }
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(5.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_scan_home),
                contentDescription = "Scan",
                modifier = Modifier.size(55.dp)
            )
            // Label for Scan button
            Text(
                text = "Scan",
                fontSize = 12.sp,
                color = if (currentDestination == AppDestinations.Scan) ColorPrimary else Color(
                    0xFFB0BEC5
                ),
            )
        }
    }
}


@Composable
fun BottomNavItem(
    modifier: Modifier = Modifier,
    destination: AppDestinations,
    currentDestination: AppDestinations,
    onNavigate: (AppDestinations) -> Unit
) {
    val isSelected = currentDestination == destination

    Box(

        contentAlignment = Alignment.TopCenter,
        modifier = modifier
            .size(70.dp)
            .padding(top = 15.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onNavigate(destination) }

    ) {
        AnimatedVisibility(
            modifier = Modifier.padding(bottom = 2.dp),
            visible = isSelected,
            enter = slideInVertically(initialOffsetY = { -it }) + fadeIn(),
            exit = slideOutVertically(targetOffsetY = { -it }) + fadeOut(),
        ) {
            Box(
                modifier = Modifier
                    .width(17.dp)
                    .height(1.5.dp)
                    .background(ColorPrimary, RoundedCornerShape(2.dp))
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 5.dp)
        ) {
            Icon(
                painter = painterResource(
                    id = if (isSelected) destination.selectedIcon else destination.unselectedIcon
                ),
                contentDescription = destination.label,
                tint = if (isSelected) ColorPrimary else Color(0xFFB0BEC5),
                modifier = Modifier.size(24.dp)
            )

            Text(
                text = destination.label,
                fontSize = 12.sp,
                color = if (isSelected) ColorPrimary else Color(0xFFB0BEC5),
                fontWeight = if (isSelected) FontWeight.Medium else FontWeight.Normal,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

enum class AppDestinations(
    val label: String,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int,
) {
    // TODO: Add your vector drawable resources here
    HOME("Home", R.drawable.ic_home_filled, R.drawable.ic_home_outlined),
    Wallet("Wallet", R.drawable.ic_wallet_filled, R.drawable.ic_wallet_outlined),
    Scan("Scan", R.drawable.ic_scan_home, R.drawable.ic_scan_home), // Placeholder icon
    Offers("Offers", R.drawable.ic_offers_filled, R.drawable.ic_offers_outlined),
    Profile("Profile", R.drawable.ic_profile_filled, R.drawable.ic_profile_outlined),
}

@Preview(showBackground = true)
@Composable
fun HomeActivityPreview() {
    CloseLoopWalletTheme {
        HomeActivityContent(
            currentDestination = AppDestinations.HOME,
            onNavigate = {},
            screenContent = {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    HomeContentPreview()
                }
            }
        )
    }
}
