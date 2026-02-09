package com.rabbah.clw.presentation.login

import android.content.Intent
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.rabbah.clw.presentation.home.HomeActivity
import com.rabbah.clw.presentation.signup.CompleteProfileActivity
import com.rabbah.clw.presentation.theme.ColorPrimary
import com.rabbah.clw.presentation.utils.UiState
import com.rabbah.domain.model.network.response.UserDto
import kotlinx.coroutines.delay

@Composable
fun VerifyOtpScreen(
    loginViewModel: LoginViewModel,
    phoneNumber: String,
    onChangePhoneNumberSelected: () -> Unit
) {
    val context = LocalContext.current
    var otpValue by remember {
        mutableStateOf(
            if (BuildConfig.DEBUG) "123456" else ""
        )
    }
    val otpUiState by loginViewModel.otpUiState.collectAsState()
    val loginUiState by loginViewModel.loginUiState.collectAsState()

    // Timer state
    var timeLeft by remember { mutableIntStateOf(60) }
    var isTimerRunning by remember { mutableStateOf(true) }

    LaunchedEffect(isTimerRunning) {
        if (isTimerRunning) {
            while (timeLeft > 0) {
                delay(1000L)
                timeLeft--
            }
            isTimerRunning = false
        }
    }

    LaunchedEffect(otpValue) {
        if (otpValue.length == 6) {
            loginViewModel.verifyOtp(otp = otpValue, phoneNumber = phoneNumber)
        }
    }

    // Handle OTP verification result
    LaunchedEffect(otpUiState) {
        when (val state = otpUiState) {
            is UiState.Success -> {
                val userDto = state.data
                if (!userDto.isActive) {
                    Toast.makeText(context, "Your account is inactive. Please contact support.", Toast.LENGTH_LONG).show()
                    (context as? ComponentActivity)?.finish()
                } else if (!userDto.isProfileComplete) {
                    val intent = Intent(context, CompleteProfileActivity::class.java)
                    context.startActivity(intent)
                    (context as? ComponentActivity)?.finish()
                } else {
                    val intent = Intent(context, HomeActivity::class.java)
                    context.startActivity(intent)
                    (context as? ComponentActivity)?.finish()
                }
            }

            is UiState.Error -> {
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }

            else -> Unit
        }
    }
    
    // Handle OTP resend result from loginUiState
    LaunchedEffect(loginUiState) {
        when (loginUiState) {
            is UiState.Success -> {
                Toast.makeText(context, "A new OTP has been sent.", Toast.LENGTH_SHORT).show()
                // Restart timer
                timeLeft = 60
                isTimerRunning = true
                loginViewModel.resetLoginState() // Reset state to avoid re-triggering on recomposition
            }
            is UiState.Error -> {
                Toast.makeText(context, (loginUiState as UiState.Error).message, Toast.LENGTH_SHORT).show()
                loginViewModel.resetLoginState() // Reset state
            }
            else -> Unit
        }
    }

    BackHandler(enabled = true) {
        loginViewModel.resetOtpState()
        onChangePhoneNumberSelected()
    }

    // Mask phone number logic
    val maskedPhoneNumber = if (phoneNumber.length > 3) {
        val lastThree = phoneNumber.takeLast(3)
        "*****$lastThree"
    } else {
        phoneNumber
    }

    VerifyOtpContent(
        otpValue = otpValue,
        onOtpChange = { otpValue = it },
        phoneNumber = maskedPhoneNumber,
        otpUiState = otpUiState,
        isResending = loginUiState is UiState.Loading, // Use login state for loading indicator
        timeLeft = timeLeft,
        isTimerRunning = isTimerRunning,
        onResendClick = {
            otpValue = ""
            loginViewModel.resendOtp()
        },
        onChangePhoneNumberClick = {
            loginViewModel.resetOtpState()
            onChangePhoneNumberSelected()
        }
    )
}


@Composable
fun VerifyOtpContent(
    otpValue: String,
    onOtpChange: (String) -> Unit,
    phoneNumber: String,
    otpUiState: UiState<UserDto>,
    isResending: Boolean,
    timeLeft: Int,
    isTimerRunning: Boolean,
    onResendClick: () -> Unit,
    onChangePhoneNumberClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        Image(
            painter = painterResource(id = R.drawable.ic_otp),
            contentDescription = "OTP Icon",
        )
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = stringResource(R.string.verify_otp),
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Color.Black
            ),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.we_ve_sent_a_verification_code_to_your_phone),
            style = MaterialTheme.typography.bodyMedium.copy(
                color = Color.Gray,
                fontSize = 14.sp
            ),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = phoneNumber,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                fontSize = 14.sp
            ),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(40.dp))

        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(R.string.enter_verification_code),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF1E232C), // Dark color
                    fontSize = 14.sp
                ),
                modifier = Modifier.padding(bottom = 12.dp)
            )

            BasicTextField(
                value = otpValue,
                onValueChange = {
                    if (it.length <= 6 && it.all { char -> char.isDigit() }) {
                        onOtpChange(it)
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                decorationBox = {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        repeat(6) { index ->
                            val char = when {
                                index >= otpValue.length -> ""
                                else -> otpValue[index].toString()
                            }
                            val isFocused = otpValue.length == index

                            Box(
                                modifier = Modifier
                                    .size(50.dp)
                                    .border(
                                        width = 1.dp,
                                        color = if (isFocused) ColorPrimary else Color(0xFFE8ECF4),
                                        shape = RoundedCornerShape(12.dp)
                                    )
                                    .background(
                                        Color(0xFFF7F8F9),
                                        RoundedCornerShape(12.dp)
                                    ), // Light gray
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = char.ifEmpty { "-" },
                                    color = if (char.isEmpty()) Color(0xFF8391A1) else Color.Black, // Placeholder vs Input color
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (isResending) {
            CircularProgressIndicator(
                modifier = Modifier.size(24.dp),
                color = ColorPrimary
            )
        } else {
            if (isTimerRunning) {
                Text(
                    text = "Resend Code in ${timeLeft}s",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color(0xFF8391A1),
                        fontSize = 14.sp
                    ),
                    textAlign = TextAlign.Center
                )
            } else {
                Text(
                    text = stringResource(R.string.resend_code),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = ColorPrimary, // Clickable color
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.clickable { onResendClick() }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Change Phone Number",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = ColorPrimary,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier.clickable { onChangePhoneNumberClick() }
        )


        Spacer(modifier = Modifier.weight(1f))

        if (otpUiState is UiState.Loading) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = ColorPrimary)
            }
        } else {
            // The verify button is intentionally removed to trigger verification automatically.
            // A spacer is added to maintain layout consistency.
            Spacer(modifier = Modifier.height(56.dp))
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun VerifyOtpScreenPreview() {
    VerifyOtpContent(
        otpValue = "123456",
        onOtpChange = {},
        phoneNumber = "*****999",
        otpUiState = UiState.Idle,
        isResending = false,
        timeLeft = 55,
        isTimerRunning = true,
        onResendClick = {},
        onChangePhoneNumberClick = {}
    )
}
