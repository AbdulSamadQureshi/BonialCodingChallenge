package com.bonial.brochure.presentation.home

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
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

    Box(modifier = Modifier.fillMaxSize().background(White)) {
        when (uiState) {
            is UiState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            is UiState.Success -> {
                val contents = (uiState as UiState.Success<List<ContentWrapperDto>>).data
                BrochuresGrid(contents)
            }
            is UiState.Error -> {
                Text(
                    text = (uiState as UiState.Error).message ?: "something went wrong, please try again later",
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.Center).padding(16.dp)
                )
            }
            else -> {}
        }
    }
}

@Composable
fun BrochuresGrid(contents: List<ContentWrapperDto>) {
    val configuration = LocalConfiguration.current
    val columns = if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) 3 else 2

    val filteredBrochures = contents.filter {
        (it.contentType == "brochure" || it.contentType == "brochurePremium") &&
                (it.content?.distance ?: Double.MAX_VALUE) <= 5.0
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        contentPadding = PaddingValues(16.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(
            items = filteredBrochures,
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
    val brochure = wrapper.content
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column {
            AsyncImage(
                model = brochure?.brochureImage,
                contentDescription = brochure?.title,
                placeholder = painterResource(id = R.drawable.ic_launcher_background),
                error = painterResource(id = R.drawable.ic_launcher_background),
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(0.7f),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = brochure?.publisher?.name ?: "Unknown Retailer",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                brochure?.title?.let {
                    Text(
                        text = it,
                        fontSize = 12.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BrochuresGridPreview() {
    CloseLoopWalletTheme {
        val mockData = listOf(
            ContentWrapperDto(
                contentType = "brochure",
                content = BrochureDto(
                    id = 1,
                    title = "Lidl Brochure",
                    brochureImage = null,
                    distance = 0.5,
                    publisher = PublisherDto(name = "Lidl")
                )
            ),
            ContentWrapperDto(
                contentType = "brochurePremium",
                content = BrochureDto(
                    id = 2,
                    title = "REWE Premium",
                    brochureImage = null,
                    distance = 1.2,
                    publisher = PublisherDto(name = "REWE")
                )
            ),
            ContentWrapperDto(
                contentType = "brochure",
                content = BrochureDto(
                    id = 3,
                    title = "Aldi Brochure",
                    brochureImage = null,
                    distance = 2.0,
                    publisher = PublisherDto(name = "Aldi")
                )
            )
        )
        BrochuresGrid(contents = mockData)
    }
}
