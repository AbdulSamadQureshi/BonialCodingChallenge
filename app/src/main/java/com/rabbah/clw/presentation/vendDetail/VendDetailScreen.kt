package com.rabbah.clw.presentation.vendDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.rabbah.clw.R
import org.maplibre.android.geometry.LatLng
import org.maplibre.android.maps.Style
import org.ramani.compose.MapLibre
import org.ramani.compose.rememberMapViewWithLifecycle
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import org.maplibre.android.plugins.annotation.Symbol
import org.ramani.compose.Symbol


// Dummy Data - Replace with your actual data model and logic
data class Product(val id: Int, val name: String, val description: String, val price: String, val imageUrl: String)

val dummyProducts = listOf(
    Product(1, "KitKat", "Chocolate Wafer", "45", "https://i.imgur.com/3q3pSK3.png"),
    Product(2, "Water Bottle", "Still Water", "45", "https://i.imgur.com/bFv3d6m.png"),
    Product(3, "Crispy Chips", "Potato Chips", "45", "https://i.imgur.com/3q3pSK3.png"),
    Product(4, "Red Bull", "Energy Drink 250ml", "45", "https://i.imgur.com/1E2bY6M.png"),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VendDetailScreen(vendId: Int) {
    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Central Library", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { onBackPressedDispatcher?.onBackPressed() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.White)
            )
        },
        containerColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {

            MachineStatusCard()
            Spacer(modifier = Modifier.height(16.dp))

            MachineImage()
            Spacer(modifier = Modifier.height(24.dp))

            ProductFilters()
            Spacer(modifier = Modifier.height(16.dp))

            ProductGrid()
            Spacer(modifier = Modifier.height(24.dp))

            Text("map", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(bottom = 8.dp))
            MapPreview()

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun MachineStatusCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF8F9FB)),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.CheckCircle, contentDescription = "Status", tint = Color(0xFF4CAF50))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Machine Status", fontWeight = FontWeight.SemiBold)
            }
            Text("Available", color = Color(0xFF4CAF50), modifier = Modifier.padding(start = 28.dp))
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.LocationOn, contentDescription = "Location", tint = Color.Gray, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Ground Floor", color = Color.Gray, fontSize = 14.sp)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(painterResource(id = R.drawable.ic_walk), contentDescription = "Distance", tint = Color.Gray, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("50m away", color = Color.Gray, fontSize = 14.sp)
                }
            }
        }
    }
}

@Composable
fun MachineImage() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(200.dp)
        .clip(RoundedCornerShape(16.dp))
    ) {
        Image(
            painter = painterResource(id = R.drawable.placeholder_error), // Replace with your image
            contentDescription = "Vending Machine",
            contentScale = Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(modifier = Modifier.fillMaxSize().padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                InfoTag(text = "ID: CL-VM-001")
                InfoTag(text = "85% full")
            }
            Spacer(modifier = Modifier.weight(1f))
            InfoTag(text = "Card", icon = Icons.Default.CreditCard)

        }
    }
}

@Composable
fun InfoTag(text: String, icon: androidx.compose.ui.graphics.vector.ImageVector? = null) {
    Row(
        modifier = Modifier
            .background(Color.Black.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (icon != null) {
            Icon(icon, contentDescription = null, tint = Color.White, modifier = Modifier.size(14.dp))
            Spacer(modifier = Modifier.width(4.dp))
        }
        Text(text, color = Color.White, fontSize = 12.sp)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductFilters() {
    val filters = listOf("All Items", "Snacks", "Drinks", "Healthy")
    var selectedFilter by remember { mutableStateOf(filters.first()) }

    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        filters.forEach { filter ->
            FilterChip(
                selected = selectedFilter == filter,
                onClick = { selectedFilter = filter },
                label = { Text(filter) }
            )
        }
    }
}

@Composable
fun ProductGrid() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.height(400.dp), // Adjust height as needed
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(dummyProducts) { product ->
            ProductCard(product)
        }
    }
}

@Composable
fun ProductCard(product: Product) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column {
            Box(modifier = Modifier
                .height(120.dp)
                .fillMaxWidth(), contentAlignment = Alignment.Center) {
                AsyncImage(
                    model = product.imageUrl,
                    contentDescription = product.name,
                    contentScale = Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Box(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(8.dp)
                        .background(Color(0xFF6750A4), RoundedCornerShape(8.dp))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text("▲ 45", color = Color.White, fontSize = 10.sp)
                }
            }
            Column(modifier = Modifier.padding(12.dp)) {
                Text(product.name, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Text(product.description, color = Color.Gray, fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun MapPreview() {
    val mapView = rememberMapViewWithLifecycle()
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(16.dp))
    ) {
        MapLibre(
            modifier = Modifier.fillMaxSize(),
            mapView = mapView,
            styleBuilder = Style.Builder().fromUri("https://maps.geoapify.com/v1/styles/osm-liberty/style.json?apiKey=a5250d9a4d6c4955a1d220c510e04ca4")
        ) {
            // Add a symbol for the vending machine
            Symbol(
                center = LatLng(24.7136, 46.6753), // Dummy location
                isDraggable = false,
                imageId = R.drawable.ic_marker,
                size = 1.0f,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VendDetailScreenPreview() {
    VendDetailScreen(vendId = 1)
}
