package com.rabbah.clw.presentation.wallet

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rabbah.clw.R
import com.rabbah.clw.presentation.theme.CloseLoopWalletTheme
import com.rabbah.clw.presentation.theme.ColorPrimary


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManageCardsScreen(
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Manage Cards",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1B132A)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color(0xFF1B132A)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                ),
                actions = {}
            )
        },
        containerColor = Color.White
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Manage Cards",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1B132A)
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Card 1
            ManagedCardItem(
                cardBrandIcon = R.drawable.ic_wallet_outlined, // Replace with your visa icon resource
                last4Digits = "8901",
                expiryDate = "08/27",
                isDefault = true
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Card 2
            ManagedCardItem(
                cardBrandIcon = R.drawable.ic_wallet_outlined, // Replace with your mastercard icon resource
                last4Digits = "8901",
                expiryDate = "08/27",
                isDefault = false
            )
        }
    }
}

@Composable
fun ManagedCardItem(
    cardBrandIcon: Int,
    last4Digits: String,
    expiryDate: String,
    isDefault: Boolean
) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        border = if (isDefault) androidx.compose.foundation.BorderStroke(
            1.dp,
            ColorPrimary
        ) else androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFEEEEEE)),
        color = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp),
        shadowElevation = if (isDefault) 2.dp else 1.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Card Brand Icon Placeholder
                Image(
                    painter = painterResource(id = cardBrandIcon),
                    contentDescription = null,
                    modifier = Modifier.size(width = 40.dp, height = 25.dp),
                    contentScale = ContentScale.Fit
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = "•••• •••• •••• $last4Digits",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF1B132A)
                    )
                    Text(
                        text = "Expires $expiryDate",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                if (isDefault) {
                    Box(
                        modifier = Modifier
                            .background(ColorPrimary, RoundedCornerShape(16.dp))
                            .padding(horizontal = 12.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "Default",
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                } else {
                    Text(
                        text = "Set Default",
                        color = ColorPrimary,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.clickable { /* Handle Set Default */ }
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "More Options",
                    tint = Color.Gray,
                    modifier = Modifier
                        .size(20.dp)
                        .clickable { /* Handle More Options */ }
                )
            }
        }
    }
}

@Preview
@Composable
fun ManageCardsScreenPreview() {
    CloseLoopWalletTheme {
        ManageCardsScreen(onBackClick = {})
    }
}
