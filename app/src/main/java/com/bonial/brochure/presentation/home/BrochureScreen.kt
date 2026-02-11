package com.bonial.brochure.presentation.home

import android.content.res.Configuration
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
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
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.request.ImageRequest
import com.bonial.brochure.R
import com.bonial.brochure.presentation.theme.CloseLoopWalletTheme
import com.bonial.brochure.presentation.theme.White
import com.bonial.brochure.presentation.utils.UiState
import com.bonial.domain.model.network.response.BrochureDto
import com.bonial.domain.model.network.response.ContentWrapperDto
import com.bonial.domain.model.network.response.PublisherDto

@Composable
fun BrochuresScreen(brochuresViewModel: BrochuresViewModel) {
    val uiState by brochuresViewModel.brochuresUiState.collectAsState()

    LaunchedEffect(Unit) {
        brochuresViewModel.getBrochures()
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .testTag("brochure_screen")
        .background(White)) {
        when (val state = uiState) {
            is UiState.Loading -> {
                BrochuresLoadingGrid()
            }

            is UiState.Success -> {
                BrochuresGrid(state.data)
            }

            is UiState.Error -> {
                Text(
                    text = state.message ?: "something went wrong, please try again later",
                    color = Color.Red,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(16.dp)
                )
            }

            else -> {}
        }
    }
}

@Composable
fun BrochuresLoadingGrid() {
    val configuration = LocalConfiguration.current
    val columns = if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) 3 else 2

    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        contentPadding = PaddingValues(16.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(10) {
            BrochureShimmerItem()
        }
    }
}

@Composable
fun BrochuresGrid(contents: List<ContentWrapperDto>) {
    val configuration = LocalConfiguration.current
    val columns = if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) 3 else 2

    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        contentPadding = PaddingValues(16.dp),
        modifier = Modifier.fillMaxSize().testTag("brochures_grid")
    ) {
        items(
            items = contents,
            key = { item -> "${item.contentType}_${item.content.firstOrNull()?.brochureImage ?: item.hashCode()}" },
            span = { item ->
                if (item.contentType == "brochurePremium") {
                    GridItemSpan(columns)
                } else {
                    GridItemSpan(1)
                }
            }
        ) { wrapper ->
            BrochureItem(wrapper)
        }
    }
}

@Composable
fun BrochureItem(wrapper: ContentWrapperDto) {
    val brochure = wrapper.content.firstOrNull() ?: return
    var isLoading by remember { mutableStateOf(true) }
    var isError by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .testTag("brochure_item_${wrapper.contentType}")
    ) {
        Box(modifier = Modifier.fillMaxWidth().aspectRatio(0.7f)) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(brochure.brochureImage)
                    .crossfade(true)
                    .build(),
                contentDescription = brochure.publisher?.name ?: "Brochure Image",
                onState = { state ->
                    isLoading = state is AsyncImagePainter.State.Loading
                    isError = state is AsyncImagePainter.State.Error
                },
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            if (isLoading) {
                Box(modifier = Modifier.fillMaxSize().shimmerEffect())
            }

            if (isError) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.LightGray.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        AsyncImage(
                            model = R.drawable.placeholder_error,
                            contentDescription = null,
                            modifier = Modifier.size(48.dp),
                            contentScale = ContentScale.Fit
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Image unavailable",
                            fontSize = 12.sp,
                            color = Color.Gray,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            // Title Overlay
            if (!brochure.title.isNullOrBlank() && !isLoading && !isError) {
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .fillMaxWidth()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f)),
                                startY = 0f
                            )
                        )
                        .padding(top = 16.dp, start = 8.dp, end = 8.dp, bottom = 8.dp)
                ) {
                    Text(
                        text = brochure.title!!,
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Composable
fun BrochureShimmerItem() {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(0.7f)
                .shimmerEffect()
        )
    }
}

fun Modifier.shimmerEffect(): Modifier = composed {
    var size by remember { mutableStateOf(IntSize.Zero) }
    val transition = rememberInfiniteTransition(label = "shimmer")
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = LinearEasing)
        ),
        label = "shimmer"
    )

    background(
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xFFEBEBEB),
                Color(0xFFD6D6D6),
                Color(0xFFEBEBEB),
            ),
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
        )
    ).onGloballyPositioned {
        size = it.size
    }
}

@Preview(showBackground = true)
@Composable
fun BrochuresGridPreview() {
    CloseLoopWalletTheme {
        val mockData = listOf(
            ContentWrapperDto(
                contentType = "brochure",
                content = listOf(
                    BrochureDto(
                        title = "Veganuary Rezepte",
                        brochureImage = null,
                        distance = 0.5,
                        publisher = PublisherDto(
                            name = "Publisher 1"
                        )
                    )
                )
            ),
            ContentWrapperDto(
                contentType = "brochurePremium",
                content = listOf(
                    BrochureDto(
                        title = "Premium Offer",
                        brochureImage = null,
                        distance = 1.2,
                        publisher = PublisherDto(
                            name = "Publisher 2"
                        )
                    )
                )
            )
        )
        BrochuresGrid(contents = mockData)
    }
}
