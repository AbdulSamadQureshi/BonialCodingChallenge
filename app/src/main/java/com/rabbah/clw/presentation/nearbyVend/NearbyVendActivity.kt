package com.rabbah.clw.presentation.nearbyVend

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.rabbah.clw.presentation.nearbyVend.list.NearbyVendListScreen
import com.rabbah.clw.presentation.nearbyVend.map.NearbyVendScreen
import com.rabbah.clw.presentation.theme.CloseLoopWalletTheme
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalPermissionsApi::class)
class NearbyVendActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CloseLoopWalletTheme {
                val nearbyVendViewModel: NearbyVendViewModel = koinViewModel()
                var isMapView by remember { mutableStateOf(false) }
                var currentLocation by remember { mutableStateOf<Pair<Double, Double>?>(null) }
                var isFetchingLocation by remember { mutableStateOf(false) }
                val context = LocalContext.current

                val permissionsState = rememberMultiplePermissionsState(
                    permissions = listOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )

                LaunchedEffect(Unit) {
                    if (!permissionsState.allPermissionsGranted) {
                        permissionsState.launchMultiplePermissionRequest()
                    }
                }

                LaunchedEffect(permissionsState.allPermissionsGranted) {
                    if (permissionsState.allPermissionsGranted) {
                        isFetchingLocation = true
                        getCurrentLocation(context) { latLng ->
                            isFetchingLocation = false
                            currentLocation = latLng
                            nearbyVendViewModel.getNearbyVend(latitude = latLng.first, longitude = latLng.second)
                        }
                    }
                }

                if (isFetchingLocation || currentLocation == null) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                } else {
                    if (isMapView) {
                        NearbyVendScreen(
                            viewModel = nearbyVendViewModel,
                            onListClick = { isMapView = false },
                            currentLocation = currentLocation!!
                        )
                    } else {
                        NearbyVendListScreen(
                            viewModel = nearbyVendViewModel,
                            onMapClick = { isMapView = true },
                            currentLocation = currentLocation!!
                        )
                    }
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun getCurrentLocation(context: Context, onLocationReceived: (Pair<Double, Double>) -> Unit) {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        fusedLocationClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
            .addOnSuccessListener { location ->
                if (location != null) {
                    onLocationReceived(Pair(location.latitude, location.longitude))
                } else {
                    Toast.makeText(context, "Unable to fetch location", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener {
                Toast.makeText(context, "Failed to get location: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
