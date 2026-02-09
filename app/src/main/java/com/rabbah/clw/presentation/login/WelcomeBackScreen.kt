package com.rabbah.clw.presentation.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rabbah.clw.BuildConfig
import com.rabbah.clw.R
import com.rabbah.clw.presentation.theme.ColorPrimary
import com.rabbah.clw.presentation.utils.UiState
import com.rabbah.domain.model.network.response.LoginDto

@Composable
fun WelcomeBackScreen(
    loginViewModel: LoginViewModel,
    onPhoneNumberEntered: (String) -> Unit
) {
    val uiState by loginViewModel.loginUiState.collectAsState()
    val context = LocalContext.current
    var phoneNumber by remember {
        mutableStateOf(
            loginViewModel.getLastUsedPhoneNumber() ?: (if (BuildConfig.DEBUG) "0540396161" else "")
        )
    }

    LaunchedEffect(uiState) {
        when (val state = uiState) {
            is UiState.Success -> {
                // On a successful login call proceed to the OTP verification screen.
                onPhoneNumberEntered(phoneNumber)
            }
            is UiState.Error -> {
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            else -> Unit
        }
    }

    WelcomeBackContent(
        uiState = uiState,
        phoneNumber = phoneNumber,
        onPhoneNumberChange = { phoneNumber = it },
        onGetStartedClick = {
            loginViewModel.login(phoneNumber)
        }
    )
}

@Composable
fun WelcomeBackContent(
    uiState: UiState<LoginDto>,
    phoneNumber: String,
    onPhoneNumberChange: (String) -> Unit,
    onGetStartedClick: () -> Unit,
) {
    val isPhoneNumberValid = phoneNumber.length == 10 && phoneNumber.startsWith("05")
    val hasValidationError = phoneNumber.isNotEmpty() && !isPhoneNumberValid

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        Image(
            painter = painterResource(id = R.drawable.bg_wallet_login),
            contentDescription = null,
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = stringResource(R.string.welcome_back),
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Color.Black
            ),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.enter_your_phone_number_to_continue),
            style = MaterialTheme.typography.bodyMedium.copy(
                color = Color.Gray,
                fontSize = 14.sp
            ),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(40.dp))

        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(R.string.phone_number),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { input ->
                    if (input.length <= 10 && input.all { it.isDigit() }) {
                        onPhoneNumberChange(input)
                    }
                },
                placeholder = { Text("05XXXXXXXX", color = Color.Gray) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                isError = hasValidationError || uiState is UiState.Error,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                ),
                supportingText = {
                    if (hasValidationError) {
                        Text(
                            text = "Enter a valid Saudi mobile number (05XXXXXXXX)",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            if (uiState is UiState.Loading) {
                CircularProgressIndicator(color = ColorPrimary)
            } else {
                Button(
                    onClick = onGetStartedClick,
                    modifier = Modifier.fillMaxSize(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = ColorPrimary),
                    enabled = isPhoneNumberValid
                ) {
                    Text(
                        text = stringResource(R.string.next),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeBackScreenPreview() {
    WelcomeBackContent(
        uiState = UiState.Idle,
        phoneNumber = "0512345678",
        onPhoneNumberChange = {},
        onGetStartedClick = {}
    )
}
