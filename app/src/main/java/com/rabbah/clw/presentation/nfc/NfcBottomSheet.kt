package com.rabbah.clw.presentation.nfc

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Contactless
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.rabbah.clw.services.NfcPaymentService
import com.rabbah.clw.presentation.theme.CloseLoopWalletTheme
import com.rabbah.clw.presentation.theme.White
import com.rabbah.data.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NfcBottomSheet(
    cardData: String,
    onDismissRequest: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                NfcPaymentService.dataToSend = cardData
            } else if (event == Lifecycle.Event.ON_PAUSE) {
                // Security: clear data when app goes to background
                NfcPaymentService.dataToSend = ""
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        // Initial set
        NfcPaymentService.dataToSend = cardData

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
            // Clear data when bottom sheet is dismissed
            NfcPaymentService.dataToSend = ""
        }
    }

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        containerColor = White,
        dragHandle = {
            Box(
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .width(40.dp)
                    .height(5.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFFE0E0E0))
            )
        }
    ) {
        Column(
            modifier = Modifier
                .background(White)
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, bottom = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ReadyToPayContent()
        }
    }
}

@Composable
fun ReadyToPayContent() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(52.dp))
        Image(
            painter = painterResource(com.rabbah.clw.R.drawable.ic_nfc),
            contentDescription = "NFC",
            modifier = Modifier.size(96.dp),
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Ready to Pay",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF111827)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Hold your phone near the device",
            fontSize = 16.sp,
            color = Color(0xFF4B5563)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF3B82F6)) // Blue dot
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Searching for nearby device...",
                fontSize = 14.sp,
                color = Color(0xFF4B5563)
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun ReadyToPayPreview() {
    CloseLoopWalletTheme {
        Column(
            modifier = Modifier
                .background(White)
                .padding(24.dp)
                .fillMaxWidth()
        ) {
            ReadyToPayContent()
        }
    }
}
