package com.rabbah.clw.presentation.more

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.LocaleListCompat
import com.rabbah.clw.R
import com.rabbah.clw.presentation.accountDetail.EditProfileActivity
import com.rabbah.clw.presentation.home.HomeActivity
import com.rabbah.clw.presentation.login.LoginActivity
import com.rabbah.clw.presentation.login.LoginViewModel
import com.rabbah.clw.presentation.theme.ColorPrimary
import com.rabbah.clw.presentation.transactions.TransactionsActivity
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(loginViewModel: LoginViewModel = koinViewModel(), profileViewModel: ProfileViewModel = koinViewModel()) {
    val context = LocalContext.current
    var pushNotificationsEnabled by remember { mutableStateOf(true) }
    var locationServicesEnabled by remember { mutableStateOf(true) }
    var showLanguageDialog by remember { mutableStateOf(false) }
    var selectedLanguage by remember { mutableStateOf(profileViewModel.getLanguage()) }

    if (showLanguageDialog) {
        LanguageSelectionDialog(
            currentLanguage = selectedLanguage,
            onLanguageSelected = {
                selectedLanguage = it
                profileViewModel.saveLanguage(it)
                val appLocale = LocaleListCompat.forLanguageTags(it)
                AppCompatDelegate.setApplicationLocales(appLocale)

                // Correctly restart the app to apply the language change across all screens
                val activity = context as? Activity
                activity?.let {
                    val intent = Intent(it, HomeActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    it.startActivity(intent)
                    it.finishAffinity() // Finish all activities in the current task
                }

                showLanguageDialog = false
            },
            onDismiss = { showLanguageDialog = false }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)) // Light gray background
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Profile Header
        ProfileHeader()

        Spacer(modifier = Modifier.height(24.dp))

        // Account Management
        Text(
            text = "Account Management",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                ProfileMenuItem(title = "Edit Profile", onClick = {
                    context.startActivity(Intent(context, EditProfileActivity::class.java))
                })
                Divider()
                ProfileMenuItem(title = "Transaction History", onClick = {
                    context.startActivity(Intent(context, TransactionsActivity::class.java))
                })
                Divider()
                ProfileMenuItem(title = "Notifications", onClick = {
                    showUnderDevelopmentToast(context)
                })
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // App Preferences
        Text(
            text = "App Preferences",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                ProfileMenuItem(title = "Language", value = if (selectedLanguage == "en") "English" else "Arabic", onClick = {
                    showLanguageDialog = true
                })
                Divider()
                ProfileSwitchItem(
                    title = "Push Notifications",
                    checked = pushNotificationsEnabled,
                    onCheckedChange = { pushNotificationsEnabled = it }
                )
                Divider()
                ProfileSwitchItem(
                    title = "Location Services",
                    checked = locationServicesEnabled,
                    onCheckedChange = { locationServicesEnabled = it }
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Logout Button
        Button(
            onClick = { 
                loginViewModel.logout()
                val intent = Intent(context, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                context.startActivity(intent)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFEBEE)) // Light Red
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Logout", color = Color(0XFFDC2626), fontSize = 14.sp, fontWeight = FontWeight.Medium)
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    painter = painterResource(R.drawable.ic_logout),
                    contentDescription = "Logout",
                    tint = Color.Red
                )
            }
        }
        
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun LanguageSelectionDialog(
    currentLanguage: String,
    onLanguageSelected: (String) -> Unit,
    onDismiss: () -> Unit
) {
    val languages = listOf("en" to "English", "ar" to "Arabic")
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Select Language") },
        text = {
            Column {
                languages.forEach { (code, name) ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .clickable { onLanguageSelected(code) }
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = currentLanguage == code,
                            onClick = { onLanguageSelected(code) }
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(name)
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Composable
fun ProfileHeader() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(ColorPrimary),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "A",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text("Ali Ahmed", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Text("ID: 2024-CS-1847", fontSize = 14.sp, color = Color.Gray)
        }
    }
}

@Composable
fun ProfileMenuItem(title: String, value: String? = null, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(title, fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (value != null) {
                Text(value, fontSize = 14.sp, color = Color.Gray)
                Spacer(modifier = Modifier.width(8.dp))
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Composable
fun ProfileSwitchItem(title: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(title, fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = ColorPrimary,
                uncheckedThumbColor = Color.White,
                uncheckedTrackColor = Color.LightGray
            )
        )
    }
}

@Composable
fun Divider() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color(0xFFEEEEEE))
    )
}

fun showUnderDevelopmentToast(context: Context) {
    Toast.makeText(context, "Under development", Toast.LENGTH_SHORT).show()
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}
