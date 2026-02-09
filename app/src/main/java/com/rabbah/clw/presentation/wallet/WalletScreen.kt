package com.rabbah.clw.presentation.wallet

import android.content.Intent
import android.nfc.NfcAdapter
import android.provider.Settings
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rabbah.clw.R
import com.rabbah.clw.presentation.nfc.NfcBottomSheet
import com.rabbah.clw.presentation.theme.CloseLoopWalletTheme
import com.rabbah.clw.presentation.theme.ColorPrimary
import com.rabbah.clw.presentation.transactionDetail.TransactionDetailActivity
import com.rabbah.clw.presentation.transactions.TransactionItem
import com.rabbah.clw.presentation.transactions.TransactionsActivity
import com.rabbah.clw.presentation.utils.UiState
import com.rabbah.clw.presentation.utils.UiUtils
import com.rabbah.domain.model.network.response.CardDto
import com.rabbah.domain.model.network.response.ProductDto
import com.rabbah.domain.model.network.response.TransactionDto
import com.rabbah.domain.model.network.response.VendDto
import com.rabbah.domain.model.network.response.WalletDto

@Composable
fun WalletScreen(walletViewModel: WalletViewModel) {
    val walletUiState by walletViewModel.walletUiState.collectAsState()
    val transactionsUiState by walletViewModel.transactionsUiState.collectAsState()
    val context = LocalContext.current
    WalletScreenContent(
        walletUiState = walletUiState,
        transactionsUiState = transactionsUiState,
        onTopUp = { walletViewModel.topUp(it) },
        onGetWallet = { walletViewModel.getWallet(it) },
        onGetTransactions = { walletViewModel.getTransactionsHistory(1) },
        onRequestCard = { walletViewModel.requestCard(it) },
        onLockCard = { userId, cardId -> walletViewModel.lockCard(userId, cardId) },
        onUnlockCard = { userId, cardId -> walletViewModel.unlockCard(userId, cardId) },
        onActivateCard = { userId, cardId -> walletViewModel.activateCard(userId, cardId) },
        onManageCardsClick = {
            context.startActivity(Intent(context, ManageCardsActivity::class.java))
        }
    )
}

@Composable
private fun WalletScreenContent(
    walletUiState: UiState<WalletDto>,
    transactionsUiState: UiState<List<TransactionDto>>,
    onTopUp: (Int) -> Unit = {},
    onGetWallet: (Int) -> Unit = {},
    onGetTransactions: (Int) -> Unit = {},
    onRequestCard: (Int) -> Unit = {},
    onLockCard: (Int, Int) -> Unit = { _, _ -> },
    onUnlockCard: (Int, Int) -> Unit = { _, _ -> },
    onActivateCard: (Int, Int) -> Unit = { _, _ -> },
    onManageCardsClick: () -> Unit = {}

) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        onGetWallet(123)
    }

    LaunchedEffect(Unit) {
        onGetTransactions(1)
    }


    var showNfcBottomSheet by rememberSaveable {
        mutableStateOf(false)
    }

    val cardData = (walletUiState as? UiState.Success)?.data?.card?.number

    if (showNfcBottomSheet && cardData != null) {
        NfcBottomSheet(
            cardData = cardData,
            onDismissRequest = { showNfcBottomSheet = false }
        )
    }

    Column(
        modifier = Modifier
            .background(Color(0XFFE5E7EB))
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "My Wallet",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 16.dp)
        )

        when (walletUiState) {
            is UiState.Loading -> {
                CircularProgressIndicator()
            }

            is UiState.Error -> {
                Text(
                    textAlign = TextAlign.Center,
                    text = walletUiState.message ?: "Wallet couldn't be loaded",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(top = 24.dp)
                )
            }

            is UiState.Success -> {
                val walletDto = walletUiState.data
                if (walletDto.isCardRequested == true) {
                    RequestedCardUi()
                } else {
                    val cardState = UiUtils.getCardState(walletDto.card)
                    if (cardState == null) {
                        NoCardUi {
                            onRequestCard(123)
                        }
                    } else {
                        CardUi(
                            walletDto = walletDto,
                            cardState = cardState,
                            onLockCard = onLockCard,
                            onUnlockCard = onUnlockCard,
                            onActivateCard = onActivateCard,
                            onTopUp = onTopUp,
                            onCardTap = {
                                val nfcAdapter = NfcAdapter.getDefaultAdapter(context)
                                if (nfcAdapter?.isEnabled == true) {
                                    showNfcBottomSheet = true
                                } else {
                                    Toast.makeText(
                                        context,
                                        "NFC is not enabled",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    val intent = Intent(Settings.ACTION_NFC_SETTINGS)
                                    if (intent.resolveActivity(context.packageManager) != null) {
                                        context.startActivity(intent)
                                    }
                                }
                            },
                            onManageCardsClick = onManageCardsClick
                        )
                        TransactionsContent(
                            transactionsUiState = transactionsUiState,
                            onItemClick = {
                                context.startActivity(TransactionDetailActivity.createIntent(context, it.id))
                            },
                            onViewAllClick = {
                                context.startActivity(
                                    Intent(
                                        context,
                                        TransactionsActivity::class.java
                                    )
                                )
                            })
                    }
                }
            }

            else -> Unit
        }
    }
}

@Composable
fun CardUi(
    walletDto: WalletDto,
    cardState: CardState,
    onLockCard: (Int, Int) -> Unit = { _, _ -> },
    onUnlockCard: (Int, Int) -> Unit = { _, _ -> },
    onActivateCard: (Int, Int) -> Unit = { _, _ -> },
    onTopUp: (Int) -> Unit,
    onCardTap: () -> Unit,
    onManageCardsClick: () -> Unit

) {
    val context = LocalContext.current
    VirtualCard(walletDto.card!!, cardState, onCardTap = {
        onCardTap.invoke()
    })

    Spacer(modifier = Modifier.height(16.dp))

    Button(
        onClick = {
            Toast.makeText(context, "Under Development", Toast.LENGTH_SHORT).show()
            onTopUp(123)
        },
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
        border = BorderStroke(1.dp, ColorPrimary),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_add), // Assuming you have an add icon
            contentDescription = null,
            tint = ColorPrimary,
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            "Top Up Wallet",
            fontSize = 16.sp,
            color = Color(0xFF5D4A99),
            fontWeight = FontWeight.Medium
        )
    }

    Spacer(modifier = Modifier.height(24.dp))

    Text(
        text = "Manage Cards",
        color = Color(0xFF5D4A99),
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        modifier = Modifier.clickable { onManageCardsClick() }
    )

    Spacer(modifier = Modifier.height(16.dp))

    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        // Lock Card Button
        ManageCardButton(
            icon = if (cardState == CardState.Locked) R.drawable.ic_lock else R.drawable.ic_unlock, // Replace with appropriate icons
            text = if (cardState == CardState.Locked) "Unlock Card" else "Lock Card",
            modifier = Modifier.weight(1f),
            onClick = {
                if (cardState == CardState.Locked) {
                    onUnlockCard(123, 123)
                } else {
                    onLockCard(123, 123)
                }
            }
        )

        // Activate Button
        ManageCardButton(
            icon = R.drawable.ic_activate, // Replace with appropriate icon
            text = "Activate",
            modifier = Modifier.weight(1f),
            onClick = {
                onActivateCard(123, 123)
            }
        )
    }
}

@Composable
fun TransactionsContent(
    transactionsUiState: UiState<List<TransactionDto>>,
    onViewAllClick: () -> Unit,
    onItemClick: (TransactionDto) -> Unit
) {
    Spacer(modifier = Modifier.height(29.dp))
    val showViewAll = remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth().wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = "Recent transactions",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start
        )
        Text(
            text = "View All",
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = ColorPrimary,
            textAlign = TextAlign.Start,
            modifier = Modifier.clickable { onViewAllClick() }
        )
    }


    Spacer(modifier = Modifier.height(16.dp))

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
                TransactionsList(transactions = transactions, onItemClick =  {
                    onItemClick(it)
                })
            }
        }

        else -> {
            showViewAll.value = false
            NoTransactionsUi()
        }
    }
}

@Composable
fun TransactionsList(transactions: List<TransactionDto>, onItemClick: (transactionDto: TransactionDto) -> Unit) {
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
fun ManageCardButton(
    @DrawableRes icon: Int,
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .height(80.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        border = BorderStroke(1.dp, Color(0xFFEEEEEE))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = ColorPrimary,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF111827)
            )
        }
    }
}

@Composable
private fun NoCardUi(onRequestCard: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "You don't have any card",
            color = Color.Gray,
            style = MaterialTheme.typography.bodyLarge
        )

        Button(
            onClick = {
                onRequestCard.invoke()
            },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text("Request Card")
        }
    }
}

@Composable
private fun RequestedCardUi() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Card is already requested, waiting for Admin to approve",
            color = Color.Gray,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun VirtualCard(cardDto: CardDto, cardState: CardState, onCardTap: (() -> Unit)? = null) {


    with(cardDto) {
        // format card number into groups of 4 (handles any digits / spaces input)
        val cleaned = number.filter { it.isDigit() }
        val grouped = cleaned.chunked(4).joinToString("   ") // wider spacing

        Box(
            modifier = Modifier
                .height(170.dp)
                .fillMaxWidth()
                .clickable {
                    if (cardState == CardState.Unlocked) {
                        onCardTap?.invoke()
                    }
                },
        ) {

            Image(
                painter = painterResource(R.drawable.bg_card),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds,

                contentDescription = "Card"
            )
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {


                Column(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(top = 24.dp, start = 24.dp)
                ) {
                    // Top Left Name and Number

                    Text(
                        text = cardDto.ownerName, // Assuming 'name' is owner name, design has "Ali Ahmed"
                        fontSize = 12.sp,
                        color = Color(0XFFF0F0FF)
                    )
                    Text(
                        text = "4532  7228  ***  ***", // Masked number for privacy/design match? or use actual number
                        // text = grouped, // Use this for actual number
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0XFFF0F0FF),
                        letterSpacing = 1.sp
                    )

                }

                // Bottom Left Balance
                Column(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(start = 24.dp, bottom = 24.dp),

                    ) {
                    Text(
                        text = "Your Balance",
                        fontSize = 12.sp,
                        color = Color(0XFFF0F0FF)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "₦122.75", // Hardcoded balance as per design request, replace with dynamic data if available
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0XFFF0F0FF)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.ic_reveal), // Eye icon
                            contentDescription = "Show Balance",
                            tint = Color.White,
                            modifier = Modifier
                                .padding(start = 9.dp)
                                .size(24.dp)
                        )
                    }

                }
            }
            // top right wallet icon
            Image(
                painter = painterResource(id = R.drawable.ic_wallet_outlined), // Replace with a generic wallet icon
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.White),
                modifier = Modifier
                    .padding(top = 24.dp, end = 24.dp)
                    .size(24.dp)
                    .align(Alignment.TopEnd)
            )
        }
    }
}

enum class CardState {
    Suspended, Locked, Unlocked
}

@Preview(showBackground = true)
@Composable
fun WalletScreenPreview() {
    CloseLoopWalletTheme {
        val mockWalletDto = WalletDto(
            walletBalance = 122.75,
            card = CardDto(
                number = "4532722812345678",
                ownerName = "Ali Ahmed",
                expiryDate = "12/26",
                cvv = "123",
                type = "VISA"
            )
        )

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

        WalletScreenContent(
            walletUiState = UiState.Success(mockWalletDto),
            transactionsUiState = UiState.Success(mockTransactions)
        )
    }
}
