package com.ab.fordhub.common

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import com.ab.fordhub.R
import com.ab.fordhub.model.VehicleDetail
import com.ab.fordhub.model.VehicleService
import com.ab.fordhub.ui.theme.ford_color_translucent_black
import com.ab.fordhub.util.Constants
import com.ab.fordhub.util.carouselTransition
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeMediaCarousel(
    list: List<VehicleDetail>,
    totalItemsToShow: Int = 10,
    carouselLabel: String = "",
    autoScrollDuration: Long = Constants.CAROUSEL_AUTO_SCROLL_TIMER,
    onItemClicked: (VehicleDetail) -> Unit
) {
    val pageCount = list.size.coerceAtMost(totalItemsToShow)
    val pagerState: PagerState = rememberPagerState(pageCount = { pageCount })
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()
    if (isDragged.not()) {
        with(pagerState) {
            if (pageCount > 0) {
                var currentPageKey by remember { mutableIntStateOf(0) }
                LaunchedEffect(key1 = currentPageKey) {
                    launch {
                        delay(timeMillis = autoScrollDuration)
                        val nextPage = (currentPage + 1).mod(pageCount)
                        animateScrollToPage(page = nextPage)
                        currentPageKey = nextPage
                    }
                }
            }
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box {
            HorizontalPager(
                state = pagerState,
                contentPadding = PaddingValues(
                    horizontal = dimensionResource(id = R.dimen.double_padding)
                ),
                pageSpacing = dimensionResource(id = R.dimen.normal_padding)
            ) { page: Int ->
                val item: VehicleDetail? = list[page]
                item?.let {
                    Card(
                        onClick = { onItemClicked(it) },
                        modifier = Modifier
                            .carouselTransition(page, pagerState)
                    ) {
                        CarouselItem(it.name,it.url)
                    }
                }
            }
        }

        if (carouselLabel.isNotBlank()) {
            Text(text = carouselLabel, style = MaterialTheme.typography.labelSmall, modifier = Modifier.padding(top = 5.dp))
        }
    }
}

@Composable
fun CarouselItem(itemName : String,imageUrl : String) {
    Box {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            placeholder = painterResource(id = R.drawable.ic_load_placeholder),
            error = painterResource(id = R.drawable.ic_load_error),
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .height(dimensionResource(id = R.dimen.home_grid_poster_height))
                .fillMaxWidth()
        )
        val gradient = remember {
            Brush.verticalGradient(listOf(Color.Transparent, ford_color_translucent_black))
        }

        Text(
            text = itemName,
            color = Color.White,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(gradient)
                .padding(top = 5.dp)
                .padding(
                    horizontal = dimensionResource(id = R.dimen.normal_padding),
                    vertical = dimensionResource(id = R.dimen.small_padding)
                )
        )
    }
}


@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun VehicleServiceCarousel(
    list: List<VehicleService>,
    totalItemsToShow: Int = 10,
    carouselLabel: String = "",
    autoScrollDuration: Long = Constants.CAROUSEL_AUTO_SCROLL_TIMER,
    onItemClicked: (VehicleService) -> Unit
) {
    val pageCount = list.size.coerceAtMost(totalItemsToShow)
    val pagerState: PagerState = rememberPagerState(pageCount = { pageCount })
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()
    if (isDragged.not()) {
        with(pagerState) {
            if (pageCount > 0) {
                var currentPageKey by remember { mutableIntStateOf(0) }
                LaunchedEffect(key1 = currentPageKey) {
                    launch {
                        delay(timeMillis = autoScrollDuration)
                        val nextPage = (currentPage + 1).mod(pageCount)
                        animateScrollToPage(page = nextPage)
                        currentPageKey = nextPage
                    }
                }
            }
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box {
            HorizontalPager(
                state = pagerState,
                contentPadding = PaddingValues(
                    horizontal = dimensionResource(id = R.dimen.double_padding)
                ),
                pageSpacing = dimensionResource(id = R.dimen.normal_padding)
            ) { page: Int ->
                val item: VehicleService? = list[page]
                item?.let {
                    Card(
                        onClick = { onItemClicked(it) },
                        modifier = Modifier
                            .carouselTransition(page, pagerState)
                    ) {
                        CarouselItem("",it.url)
                    }
                }
            }
        }

        if (carouselLabel.isNotBlank()) {
            Text(text = carouselLabel, style = MaterialTheme.typography.labelSmall, modifier = Modifier.padding(top = 5.dp))
        }
    }
}