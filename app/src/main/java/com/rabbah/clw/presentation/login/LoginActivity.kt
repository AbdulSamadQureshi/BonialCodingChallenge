package com.rabbah.clw.presentation.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.rabbah.clw.presentation.signup.SignupIntroScreen
import com.rabbah.clw.presentation.theme.CloseLoopWalletTheme
import org.koin.androidx.compose.koinViewModel

class LoginActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: LoginViewModel = koinViewModel()
            CloseLoopWalletTheme {
                // Check if a user is already saved to determine the starting screen.
                val lastUsedPhoneNumber = remember { viewModel.getLastUsedPhoneNumber() }

                // State to manage which screen is shown
                // 0: Intro, 1: Welcome/Login, 2: OTP
                var currentStep by remember {
                    mutableStateOf(if (lastUsedPhoneNumber != null) 1 else 0)
                }
                var phoneNumber by remember { mutableStateOf(lastUsedPhoneNumber ?: "") }

                when (currentStep) {
                    0 -> {
                        SignupIntroScreen(
                            onGetStartedClick = {
                                currentStep = 1
                            }
                        )
                    }
                    1 -> {
                        WelcomeBackScreen(
                            loginViewModel = viewModel,
                            onPhoneNumberEntered = { inputPhoneNumber ->
                                phoneNumber = inputPhoneNumber
                                currentStep = 2
                                // Reset the state immediately after the event is handled
                                viewModel.resetLoginState()
                            }
                        )
                    }
                    2 -> {
                        VerifyOtpScreen(
                            loginViewModel = viewModel,
                            phoneNumber = phoneNumber,
                            onChangePhoneNumberSelected = {
                                currentStep = 1
                                // Reset the state to prevent being stuck in a success loop
                                viewModel.resetLoginState()
                            }
                        )
                    }
                }
            }
        }
    }
}
