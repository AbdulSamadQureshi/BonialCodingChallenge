package com.bonial.brochure.presentation.home

import android.content.res.Configuration
import androidx.compose.animation.core.LinearEasing
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
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.res.stringResource
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
import com.bonial.brochure.presentation.model.BrochureUi
import com.bonial.brochure.presentation.theme.CloseLoopWalletTheme
import com.bonial.brochure.presentation.theme.White
import com.bonial.brochure.presentation.utils.UiState

/**
 * Main screen for displaying brochures.
 * Highly decoupled from specific business models by passing data down as primitive types to atomic components.
 */
@Composable
fun BrochuresScreen(brochuresViewModel: BrochuresViewModel) {
    val uiState by brochuresViewModel.brochuresUiState.collectAsState()

    BrochuresContent(
        uiState = uiState,
        modifier = Modifier
            .fillMaxSize()
            .testTag("brochure_screen")
            .background(White)
    )
}

@Composable
private fun BrochuresContent(
    uiState: UiState<List<BrochureUi>>,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        when (uiState) {
            is UiState.Loading -> BrochuresLoadingGrid()
            is UiState.Success -> BrochuresGrid(brochures = uiState.data)
            is UiState.Error -> ErrorMessage(message = uiState.message)
            else -> {}
        }
    }
}

@Composable
fun ErrorMessage(message: String?) {
    Text(
        text = message ?: stringResource(R.string.error_generic),
        color = Color.Red,
        modifier = Modifier.padding(16.dp)
    )
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
fun BrochuresGrid(
    brochures: List<BrochureUi>,
    modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current
    val columns = if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) 3 else 2

    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        contentPadding = PaddingValues(16.dp),
        modifier = modifier
            .fillMaxSize()
            .testTag("brochures_grid")
    ) {
        items(
            items = brochures,
            key = { item -> item.coverUrl ?: item.hashCode() }
        ) { brochure ->
            // Passed as independent primitive types to maintain atomicity
            BrochureItem(
                title = brochure.title,
                imageUrl = brochure.coverUrl,
                publisherName = brochure.publisherName
            )
        }
    }
}

/**
 * Atomic component for a single brochure item.
 * Independent of the [BrochureUi] class.
 */
@Composable
fun BrochureItem(
    title: String?,
    imageUrl: String?,
    publisherName: String?,
    modifier: Modifier = Modifier
) {
    var isLoading by remember { mutableStateOf(true) }
    var isError by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .testTag("brochure_item")
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(0.7f)
        ) {
            BrochureImage(
                imageUrl = imageUrl,
                contentDescription = publisherName,
                onStateChanged = { state ->
                    isLoading = state is AsyncImagePainter.State.Loading
                    isError = state is AsyncImagePainter.State.Error
                }
            )

            if (isLoading) {
                Box(modifier = Modifier.fillMaxSize().shimmerEffect())
            }

            if (isError) {
                ImageErrorPlaceholder()
            }

            if (!title.isNullOrBlank() && !isLoading && !isError) {
                BrochureTitleOverlay(
                    title = title,
                    modifier = Modifier.align(Alignment.BottomCenter)
                )
            }
        }
    }
}

@Composable
fun BrochureImage(
    imageUrl: String?,
    contentDescription: String?,
    onStateChanged: (AsyncImagePainter.State) -> Unit,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = contentDescription ?: stringResource(R.string.content_desc_brochure_image),
        onState = onStateChanged,
        modifier = modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun ImageErrorPlaceholder(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .testTag("error_placeholder")
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
                text = stringResource(R.string.error_image_unavailable),
                fontSize = 12.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun BrochureTitleOverlay(title: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
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
            text = title,
            color = Color.White,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun BrochureShimmerItem(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
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
private fun BrochuresGridPreview() {
    CloseLoopWalletTheme {
        val mockData = listOf(
            BrochureUi(
                id = 1,
                title = "Veganuary Rezepte",
                publisherName = "Publisher 1",
                coverUrl = null,
                distance = 0.5
            ),
            BrochureUi(
                id = 2,
                title = "Premium Offer",
                publisherName = "Publisher 2",
                coverUrl = null,
                distance = 1.2
            )
        )
        BrochuresGrid(brochures = mockData)
    }
}
