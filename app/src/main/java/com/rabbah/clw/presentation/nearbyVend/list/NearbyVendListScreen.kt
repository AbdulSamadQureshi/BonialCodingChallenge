package com.rabbah.clw.presentation.nearbyVend.list

import android.Manifest
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Map
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.rabbah.clw.R
import com.rabbah.clw.presentation.nearbyVend.NearbyVendViewModel
import com.rabbah.clw.presentation.utils.UiState
import com.rabbah.clw.presentation.vendDetail.VendDetailActivity
import com.rabbah.domain.model.network.response.VendDto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NearbyVendListScreen(
    viewModel: NearbyVendViewModel,
    onMapClick: () -> Unit,
    currentLocation: Pair<Double, Double>
) {
    val nearbyVendState by viewModel.nearbyVendState.collectAsState()
    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    val context = LocalContext.current

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
                    IconButton(onClick = onMapClick) {
                        Icon(imageVector = Icons.Default.Map, contentDescription = "Map View")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.White)
            )
        },
        containerColor = Color.White
    ) { paddingValues ->
        NearbyVendListContent(
            uiState = nearbyVendState,
            currentLocation = currentLocation,
            onRetry = {
                viewModel.getNearbyVend(
                    query = "",
                    currentLocation.first,
                    currentLocation.second
                )
            },
            onSortSelected = { option ->
                if (currentLocation != null) {
                    viewModel.getNearbyVend(
                        query = "",
                        currentLocation.first,
                        currentLocation.second
                    )
                }
            },
            onVendClick = { vendId ->
                context.startActivity(VendDetailActivity.createIntent(context, vendId))
            },
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
private fun NearbyVendListContent(
    uiState: UiState<List<VendDto>>,
    currentLocation: Pair<Double, Double>?,
    onRetry: () -> Unit,
    onSortSelected: (String) -> Unit,
    onVendClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()

    // Display Logic
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {

        // Search
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

        // Result Count & Sort
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

        when (uiState) {
            is UiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            is UiState.Error -> {
                ErrorUi(onRetry)
            }
            is UiState.Success -> {
                LazyColumn(
                    state = listState,
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(bottom = 16.dp)
                ) {
                    items(
                        items = uiState.data,
                        key = { it.id } // Use stable key to avoid duplication issues if keys are unique
                    ) { vend ->
                        VendingMachineListItem(
                            vend = vend,
                            onClick = { onVendClick(vend.id) }
                        )
                    }
                }
            }
            UiState.Idle -> {
                // Initial State
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
fun VendingMachineListItem(vend: VendDto, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(vend.image)
                    .placeholder(R.drawable.placeholder_profile)
                    .error(R.drawable.placeholder_error)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.LightGray)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = vend.title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Text(
                        text = if (vend.available) "Available" else "Busy",
                        fontSize = 12.sp,
                        color = if (vend.available) Color(0xFF4CAF50) else Color(0xFFFF9800),
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .background(
                                if (vend.available) Color(0xFFE8F5E9) else Color(0xFFFFF3E0),
                                RoundedCornerShape(4.dp)
                            )
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Row {
                    Text(text = vend.address ?: "", fontSize = 12.sp, color = Color.Gray)
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(text = vend.distance ?: "", fontSize = 12.sp, color = Color.Gray)
                }
            }
        }
    }
}

@Composable
private fun ErrorUi(onRetry: (() -> Unit)? = null) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Locations couldn't be loaded, retry?")
        Button(
            modifier = Modifier.padding(top = 16.dp),
            onClick = { onRetry?.invoke() }
        ) {
            Text("Retry")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NearbyVendListContentPreview() {
    NearbyVendListContent(
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
        onSortSelected = {},
        onVendClick = {}
    )
}
