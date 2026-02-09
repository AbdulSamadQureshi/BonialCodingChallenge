package com.rabbah.clw.presentation.nearbyVend.map

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.rabbah.clw.R
import com.rabbah.clw.presentation.nearbyVend.NearbyVendViewModel
import com.rabbah.clw.presentation.utils.UiState
import com.rabbah.domain.model.network.response.VendDto
import org.maplibre.android.camera.CameraUpdateFactory
import org.maplibre.android.geometry.LatLng
import org.maplibre.android.maps.Style
import org.ramani.compose.MapLibre
import org.ramani.compose.Symbol
import org.ramani.compose.rememberMapViewWithLifecycle

@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
@Composable
fun NearbyVendScreen(
    viewModel: NearbyVendViewModel,
    onListClick: () -> Unit,
    currentLocation: Pair<Double, Double>
) {
    val nearbyVendState by viewModel.nearbyVendState.collectAsState()
    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(stringResource(R.string.all_vending_machines), fontWeight = FontWeight.Bold, fontSize = 18.sp) },
                navigationIcon = {
                    IconButton(onClick = { onBackPressedDispatcher?.onBackPressed() }) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = onListClick) {
                        Icon(imageVector = Icons.Default.List, contentDescription = "List View")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = androidx.compose.ui.graphics.Color.White)
            )
        },
        containerColor = androidx.compose.ui.graphics.Color.White
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            NearbyVendContent(
                uiState = nearbyVendState,
                currentLocation = currentLocation,
                onRetry = {
                    viewModel.getNearbyVend(latitude = currentLocation.first, longitude = currentLocation.second)
                },
                onSortSelected = { option ->
                    // Handle sort
                    viewModel.getNearbyVend(latitude = currentLocation.first, longitude = currentLocation.second)
                }
            )
        }
    }
}

@Composable
private fun NearbyVendContent(
    uiState: UiState<List<VendDto>>,
    currentLocation: Pair<Double, Double>,
    onRetry: () -> Unit,
    onSortSelected: (String) -> Unit
) {
    var currentVends by remember { mutableStateOf<List<VendDto>>(emptyList()) }

    LaunchedEffect(uiState) {
        if (uiState is UiState.Success) {
            currentVends = uiState.data
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp)) {
        // Search Bar (same as list screen)
        OutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = { Text(stringResource(R.string.search_by_product)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            shape = RoundedCornerShape(12.dp),
            singleLine = true
        )

        // Result Count & Sort (same as list screen)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SortDropDown(onSortSelected)

            if (uiState is UiState.Success) {
                Text("${uiState.data.size} machines found", fontSize = 14.sp, color = Color.Gray)
            }
        }

        Box(modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))) {

            NearbyVendMap(currentVends, currentLocation)

            val isLoading = uiState is UiState.Loading
            if (isLoading) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background.copy(alpha = 0.5f)),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            }

            if (uiState is UiState.Error && currentVends.isEmpty()) {
                ErrorUi { onRetry.invoke() }
            }
        }
    }
}

@Composable
fun SortDropDown(onSortSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("Distance") }
    val options = listOf("Distance", "Option 1", "Option 2")

    Box {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable { expanded = true }
        ) {
            Text(text = "Sort by: ", fontSize = 14.sp, color = Color.Gray)
            Text(text = selectedOption, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "Sort", tint = Color.Gray)
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(Color.White)
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        selectedOption = option
                        expanded = false
                        onSortSelected(option)
                    }
                )
            }
        }
    }
}

@Composable
private fun NearbyVendMap(vends: List<VendDto>, currentLocation: Pair<Double, Double>) {
    val mapView = rememberMapViewWithLifecycle()

    Box(modifier = Modifier.fillMaxSize()) {
        MapLibre(
            modifier = Modifier.fillMaxSize(),
            mapView = mapView,
            styleBuilder = Style.Builder().fromUri("https://maps.geoapify.com/v1/styles/osm-liberty/style.json?apiKey=a5250d9a4d6c4955a1d220c510e04ca4")
        ) {
            vends.forEach { vend ->
                Symbol(
                    center = LatLng(vend.latitude, vend.longitude),
                    imageId = R.drawable.ic_marker,
                    size = 1.0f
                )
            }
        }

        // Configure Map UI (Compass + Zoom controls)
        LaunchedEffect(mapView) {
            mapView.getMapAsync { map ->
                map.uiSettings.apply {
                    isCompassEnabled = true
                    isLogoEnabled = true
                }
            }
        }

        // Map Legend (Available/Busy)
        Column(
             modifier = Modifier
                 .align(Alignment.TopEnd)
                 .padding(16.dp)
                 .background(Color.White, RoundedCornerShape(8.dp))
                 .padding(8.dp)
        ) {
             Row(verticalAlignment = Alignment.CenterVertically) {
                 Box(modifier = Modifier.size(10.dp).background(Color(0xFF4CAF50), CircleShape))
                 Spacer(modifier = Modifier.width(8.dp))
                 Text("Available", fontSize = 12.sp)
             }
             Spacer(modifier = Modifier.height(4.dp))
             Row(verticalAlignment = Alignment.CenterVertically) {
                 Box(modifier = Modifier.size(10.dp).background(Color(0xFFFF9800), CircleShape))
                 Spacer(modifier = Modifier.width(8.dp))
                 Text("Busy", fontSize = 12.sp)
             }
        }


        // Zoom Controls
//        Column(
//            modifier = Modifier
//                .align(Alignment.BottomEnd)
//                .padding(16.dp),
//            verticalArrangement = Arrangement.spacedBy(12.dp)
//        ) {
//            FloatingActionButton(
//                onClick = { mapView.getMapAsync { it.animateCamera(CameraUpdateFactory.zoomIn()) } },
//                containerColor = MaterialTheme.colorScheme.primary,
//                shape = CircleShape
//            ) { Text("+") }
//
//            FloatingActionButton(
//                onClick = { mapView.getMapAsync { it.animateCamera(CameraUpdateFactory.zoomOut()) } },
//                containerColor = MaterialTheme.colorScheme.primary,
//                shape = CircleShape
//            ) { Text("-") }
//
//            FloatingActionButton(
//                onClick = {
//                    mapView.getMapAsync {
//                        it.animateCamera(
//                            CameraUpdateFactory.newLatLng(LatLng(currentLocation.first, currentLocation.second))
//                        )
//                    }
//                },
//                containerColor = MaterialTheme.colorScheme.primary,
//                shape = CircleShape
//            ) { Text("📍") }
//        }
    }
}

@Composable
private fun ErrorUi(onRetry: (() -> Unit)? = null) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Locations couldn't be loaded, retry?")
        Button(
            modifier = Modifier
                .padding(top = 16.dp)
                .widthIn(min = 100.dp),
            shape = RoundedCornerShape(8.dp),
            onClick = { onRetry?.invoke() }
        ) {
            Text("Retry")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NearbyVendContentPreview() {
    NearbyVendContent(
        uiState = UiState.Success(
            listOf(
                VendDto(
                    id = 1,
                    latitude = 24.7136,
                    longitude = 46.6753,
                    title = "Vending Machine 1",
                    address = "123 Street",
                    distance = "500m",
                    image = "",
                    available = true
                )
            )
        ),
        currentLocation = Pair(24.7136, 46.6753),
        onRetry = {},
        onSortSelected = {}
    )
}
