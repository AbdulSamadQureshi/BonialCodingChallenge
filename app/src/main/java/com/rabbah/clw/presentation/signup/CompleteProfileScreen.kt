package com.rabbah.clw.presentation.signup

import android.app.Activity
import android.content.Intent
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.rabbah.clw.R
import com.rabbah.clw.presentation.accountDetail.AccountDetailViewModel
import com.rabbah.clw.presentation.home.HomeActivity
import com.rabbah.clw.presentation.theme.ColorPrimary
import com.rabbah.clw.presentation.utils.UiState
import com.rabbah.domain.model.network.response.UserDto
import org.koin.androidx.compose.koinViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompleteProfileScreen(
    viewModel: AccountDetailViewModel = koinViewModel()
) {
    var fullName by remember { mutableStateOf("") }
    var emailAddress by remember { mutableStateOf("") }
    var dateOfBirth by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }

    var fullNameError by remember { mutableStateOf<String?>(null) }
    var emailError by remember { mutableStateOf<String?>(null) }
    var dobError by remember { mutableStateOf<String?>(null) }
    var addressError by remember { mutableStateOf<String?>(null) }
    var phoneError by remember { mutableStateOf<String?>(null) }

    var showDatePicker by remember { mutableStateOf(false) }

    val context = LocalContext.current

    val datePickerState = rememberDatePickerState(
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                return utcTimeMillis <= System.currentTimeMillis()
            }
        }
    )

    val user by viewModel.user.collectAsState()
    val updateAccountDetailState by viewModel.updateAccountDetailUiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadAccountDetail()
    }

    val isSubmitEnabled = remember(fullName, emailAddress, dateOfBirth, address, phone, emailError, phoneError) {
        fullName.isNotBlank() && emailAddress.isNotBlank() && dateOfBirth.isNotBlank() && address.isNotBlank() && phone.isNotBlank() && emailError == null && phoneError == null
    }

    LaunchedEffect(updateAccountDetailState) {
        when (val state = updateAccountDetailState) {
            is UiState.Success -> {
                (context as? Activity)?.finish()
                context.startActivity(Intent(context, HomeActivity::class.java))
            }

            is UiState.Error -> {
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }

            else -> {}
        }
    }

    fun validate(): Boolean {
        fullNameError = if (fullName.isBlank()) "Full name is required" else null
        emailError = if (emailAddress.isBlank()) "Email is required" else if (!Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()) "Invalid email address" else null
        dobError = if (dateOfBirth.isBlank()) "Date of birth is required" else null
        addressError = if (address.isBlank()) "Address is required" else null
        phoneError = if (phone.isBlank()) "Phone number is required" else if (phone.length != 10 || !phone.startsWith("05")) "Enter a valid Saudi mobile number (05XXXXXXXX)" else null
        return fullNameError == null && emailError == null && dobError == null && addressError == null && phoneError == null
    }

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    datePickerState.selectedDateMillis?.let { millis ->
                        val date = Date(millis)
                        val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
                        dateOfBirth = formatter.format(date)
                        if (dobError != null) dobError = null
                    }
                    showDatePicker = false
                }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    CompleteProfileContent(
        user = user,
        fullName = fullName,
        onFullNameChange = { 
            fullName = it
            if (fullNameError != null) fullNameError = null
         },
        emailAddress = emailAddress,
        onEmailAddressChange = { 
            emailAddress = it 
            emailError = if (it.isNotBlank() && !Patterns.EMAIL_ADDRESS.matcher(it).matches()) {
                "Invalid email address"
            } else {
                null
            }
        },
        dateOfBirth = dateOfBirth,
        address = address,
        onAddressChange = { 
            address = it
            if (addressError != null) addressError = null
         },
        phone = phone,
        onPhoneChange = {
            if (it.length <= 10 && it.all { char -> char.isDigit() }) {
                phone = it
                phoneError = if (it.length != 10 || !it.startsWith("05")) {
                    "Enter a valid Saudi mobile number (05XXXXXXXX)"
                } else {
                    null
                }
            }
        },
        fullNameError = fullNameError,
        emailError = emailError,
        dobError = dobError,
        addressError = addressError,
        phoneError = phoneError,
        isLoading = updateAccountDetailState is UiState.Loading,
        onDatePickerClick = { showDatePicker = true },
        isSubmitEnabled = isSubmitEnabled,
        onSubmitClick = {
            if (validate()) {
                viewModel.updateAccountDetail(fullName, emailAddress, dateOfBirth, address, phone)
            }
        }
    )
}

@Composable
fun CompleteProfileContent(
    user: UserDto?,
    fullName: String,
    onFullNameChange: (String) -> Unit,
    emailAddress: String,
    onEmailAddressChange: (String) -> Unit,
    dateOfBirth: String,
    address: String,
    onAddressChange: (String) -> Unit,
    phone: String,
    onPhoneChange: (String) -> Unit,
    fullNameError: String?,
    emailError: String?,
    dobError: String?,
    addressError: String?,
    phoneError: String?,
    isLoading: Boolean,
    onDatePickerClick: () -> Unit,
    isSubmitEnabled: Boolean,
    onSubmitClick: () -> Unit
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {

            Spacer(modifier = Modifier.height(60.dp))

            if (!user?.profileImage.isNullOrEmpty()) {
                AsyncImage(
                    model = user?.profileImage,
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .size(96.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            } else {
                Image(
                    painter = painterResource(R.drawable.ic_user_profile),
                    contentDescription = "Profile Icon",
                )
            }
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = stringResource(R.string.complete_your_profile),
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(R.string.we_need_details_to_set_up_your_account),
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    fontSize = 14.sp
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Full Name
            ProfileInputField(
                label = stringResource(R.string.full_name),
                value = fullName,
                onValueChange = onFullNameChange,
                placeholder = stringResource(R.string.enter_your_full_name),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    keyboardType = KeyboardType.Text
                ),
                isError = fullNameError != null,
                errorMessage = fullNameError
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Email Address
            ProfileInputField(
                label = stringResource(R.string.email_address),
                value = emailAddress,
                onValueChange = onEmailAddressChange,
                placeholder = stringResource(R.string.enter_your_email),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                isError = emailError != null,
                errorMessage = emailError
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Phone Number
            ProfileInputField(
                label = stringResource(R.string.phone_number),
                value = phone,
                onValueChange = onPhoneChange,
                placeholder = "05XXXXXXXX",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                isError = phoneError != null,
                errorMessage = phoneError
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Date of Birth
            ProfileInputField(
                label = stringResource(R.string.date_of_birth),
                value = dateOfBirth,
                onValueChange = { }, // Read only
                placeholder = stringResource(R.string.mm_dd_yyyy),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Filled.CalendarToday,
                        contentDescription = "Calendar",
                        tint = Color.Gray,
                        modifier = Modifier.clickable { onDatePickerClick() }
                    )
                },
                readOnly = true,
                onClick = { onDatePickerClick() },
                isError = dobError != null,
                errorMessage = dobError
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Address
            ProfileInputField(
                label = stringResource(R.string.address),
                value = address,
                onValueChange = onAddressChange,
                placeholder = stringResource(R.string.enter_your_address),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences,
                    keyboardType = KeyboardType.Text
                ),
                isError = addressError != null,
                errorMessage = addressError
            )

            Spacer(modifier = Modifier.height(40.dp))

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .height(56.dp)
                    .fillMaxWidth()

            ) {
                Button(
                    onClick = onSubmitClick,
                    enabled = isSubmitEnabled,
                    modifier = Modifier.fillMaxSize(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = ColorPrimary),
                ) {
                    Text(
                        text = stringResource(R.string.submit),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun ProfileInputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    errorMessage: String? = null,
    readOnly: Boolean = false,
    onClick: (() -> Unit)? = null
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF1E232C),
                fontSize = 14.sp
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Box {
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                placeholder = { Text(placeholder, color = Color(0xFF8391A1)) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                keyboardOptions = keyboardOptions,
                trailingIcon = trailingIcon,
                isError = isError,
                readOnly = readOnly,
                interactionSource = remember { MutableInteractionSource() }
                    .also { interactionSource ->
                        if (onClick != null) {
                            LaunchedEffect(interactionSource) {
                                interactionSource.interactions.collect {
                                    if (it is PressInteraction.Release) {
                                        onClick()
                                    }
                                }
                            }
                        }
                    }
            )
        }

        if (isError && errorMessage != null) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 8.dp, top = 4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CompleteProfileScreenPreview() {
    CompleteProfileContent(
        user = null,
        fullName = "",
        onFullNameChange = {},
        emailAddress = "",
        onEmailAddressChange = {},
        dateOfBirth = "",
        address = "",
        onAddressChange = {},
        phone = "",
        onPhoneChange = {},
        fullNameError = null,
        emailError = null,
        dobError = null,
        addressError = null,
        phoneError = null,
        isLoading = false,
        onDatePickerClick = {},
        isSubmitEnabled = false,
        onSubmitClick = {}
    )
}
