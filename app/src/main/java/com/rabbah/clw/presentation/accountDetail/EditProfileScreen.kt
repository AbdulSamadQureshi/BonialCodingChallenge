package com.rabbah.clw.presentation.accountDetail

import android.util.Patterns
import android.widget.Toast
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
import androidx.compose.material3.Text
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
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.rabbah.clw.presentation.theme.ColorPrimary
import com.rabbah.clw.presentation.utils.UiState
import com.rabbah.domain.model.network.response.UserDto
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun EditProfileScreen(viewModel: AccountDetailViewModel) {
    val user by viewModel.user.collectAsState()
    val updateAccountDetailUiState by viewModel.updateAccountDetailUiState.collectAsState()

    AccountDetailContent(
        user,
        updateAccountDetailUiState,
        onAccountDetail = {
            viewModel.loadAccountDetail()
        },
        onUpdateAccountDetail = { fullName, email, dob, address, phone ->
            viewModel.updateAccountDetail(fullName, email, dob, address, phone)
        }
    )
}

@Composable
private fun AccountDetailContent(
    user: UserDto?,
    updateAccountDetailUiState: UiState<UserDto>,
    onAccountDetail: () -> Unit,
    onUpdateAccountDetail: (String, String, String, String, String) -> Unit,
) {
    LaunchedEffect(Unit) {
        onAccountDetail.invoke()
    }

    if (updateAccountDetailUiState is UiState.Error) {
        if (LocalInspectionMode.current.not()) {
            Toast.makeText(
                LocalContext.current,
                updateAccountDetailUiState.message,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    if (user != null) {
        AccountDetailUi(
            userDto = user,
            isUpdating = updateAccountDetailUiState is UiState.Loading,
            onUpdateAccountDetail = onUpdateAccountDetail
        )
    } else {
        Column(
            modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            CircularProgressIndicator()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountDetailUi(
    userDto: UserDto,
    isUpdating: Boolean,
    onUpdateAccountDetail: (String, String, String, String, String) -> Unit
) {
    var name by remember { mutableStateOf(userDto.name ?: "") }
    var email by remember { mutableStateOf(userDto.email ?: "") }
    var dateOfBirth by remember { mutableStateOf(userDto.dateOfBirth ?: "") }
    var address by remember { mutableStateOf(userDto.address ?: "") }
    var phone by remember { mutableStateOf(userDto.phone ?: "") }
    var showDatePicker by remember { mutableStateOf(false) }

    var isNameValid by remember { mutableStateOf(name.isNotEmpty()) }
    var isEmailValid by remember { mutableStateOf(Patterns.EMAIL_ADDRESS.matcher(email).matches()) }
    var isAddressValid by remember { mutableStateOf(address.isNotEmpty()) }
    var isDateOfBirthValid by remember { mutableStateOf(dateOfBirth.isNotEmpty()) }
    val isPhoneValid by remember(phone) { mutableStateOf(phone.length == 10 && phone.startsWith("05")) }

    val isFormValid = isNameValid && isEmailValid && isAddressValid && isDateOfBirthValid && isPhoneValid


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        // Header
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (!userDto.profileImage.isNullOrEmpty()) {
                AsyncImage(
                    model = userDto.profileImage,
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(ColorPrimary),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = name.firstOrNull()?.toString() ?: "A",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text("Edit profile", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(6.dp))
                Text("ID: ${userDto.id}", color = Color.Gray)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Form Fields
        Text("Full Name", color = Color.Gray, fontSize = 14.sp)
        OutlinedTextField(
            value = name,
            onValueChange = { 
                name = it 
                isNameValid = it.isNotEmpty()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Email Address", color = Color.Gray, fontSize = 14.sp)
        OutlinedTextField(
            value = email,
            onValueChange = { 
                email = it
                isEmailValid = Patterns.EMAIL_ADDRESS.matcher(it).matches()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            shape = RoundedCornerShape(12.dp),
            isError = !isEmailValid && email.isNotEmpty(),
            supportingText = {
                if (!isEmailValid && email.isNotEmpty()) {
                    Text("Invalid email address", color = MaterialTheme.colorScheme.error)
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Phone Number", color = Color.Gray, fontSize = 14.sp)
        OutlinedTextField(
            value = phone,
            onValueChange = { input ->
                if (input.length <= 10 && input.all { it.isDigit() }) {
                    phone = input
                }
            },
            placeholder = { Text("05XXXXXXXX") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            shape = RoundedCornerShape(12.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            isError = !isPhoneValid && phone.isNotEmpty(),
            supportingText = {
                if (!isPhoneValid && phone.isNotEmpty()) {
                    Text("Enter a valid Saudi mobile number (05XXXXXXXX)", color = MaterialTheme.colorScheme.error)
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Date of Birth", color = Color.Gray, fontSize = 14.sp)
        OutlinedTextField(
            value = dateOfBirth,
            onValueChange = { },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
                .clickable { showDatePicker = true },
            shape = RoundedCornerShape(12.dp),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.CalendarToday,
                    contentDescription = "Select Date",
                    modifier = Modifier.clickable { showDatePicker = true }
                )
            },
            readOnly = true,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Address", color = Color.Gray, fontSize = 14.sp)
        OutlinedTextField(
            value = address,
            onValueChange = { 
                address = it
                isAddressValid = it.isNotEmpty()
            },
            placeholder = { Text("Enter your address") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        // Save Button
        if (isUpdating) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else {
            Button(
                onClick = {
                    onUpdateAccountDetail(name, email, dateOfBirth, address, phone)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = ColorPrimary),
                enabled = isFormValid
            ) {
                Text("Save Changes", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }


        if (showDatePicker) {
            val datePickerState = rememberDatePickerState()
            DatePickerDialog(
                onDismissRequest = { showDatePicker = false },
                confirmButton = {
                    Button(onClick = {
                        showDatePicker = false
                        datePickerState.selectedDateMillis?.let {
                            val selectedDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date(it))
                            dateOfBirth = selectedDate
                            isDateOfBirthValid = selectedDate.isNotEmpty()
                        }
                    }) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    Button(onClick = { showDatePicker = false }) {
                        Text("Cancel")
                    }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewAccountDetail() {
    val userDto = UserDto(
        id = "2024-CS-1847",
        name = "Ali Ahmed",
        email = "Rabbah@gmail.com",
        phone = "03333333333",
        dateOfBirth = "12/12/1991",
        address = "",
        profileImage = ""
    )
    AccountDetailUi(
        userDto = userDto,
        isUpdating = false,
        onUpdateAccountDetail = { _, _, _, _, _ -> }
    )
}
