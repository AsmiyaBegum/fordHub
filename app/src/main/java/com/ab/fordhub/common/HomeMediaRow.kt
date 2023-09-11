package com.ab.fordhub.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import com.ab.fordhub.R
import com.ab.fordhub.model.VehicleDetail

@Composable
fun HomeMediaRow(
    title: String,
    list: List<VehicleDetail>,
    modifier: Modifier = Modifier,
    listItemModifier: Modifier = Modifier,
    onItemClicked: (VehicleDetail) -> Unit
) {
    Column(modifier = Modifier.height(350.dp)) {
        if (list.size > 0) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.normal_padding_half))
            )
        }

        LazyColumn(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            state = rememberLazyListState()
        ) {
            items(list.size) { index ->
                list[index]?.let {
                    HomeMediaItem(it, listItemModifier, onItemClicked)
                }
            }
//            if (list..append == LoadState.Loading) {
//                item {
//                    CircularProgressIndicator(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .wrapContentWidth(Alignment.CenterHorizontally)
//                    )
//                }
//            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeMediaItem(
    homeMediaUI: VehicleDetail,
    modifier: Modifier = Modifier,
    onItemClicked: (VehicleDetail) -> Unit
) {
    ElevatedCard(
        onClick = { onItemClicked(homeMediaUI) },
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.normal_padding_half))
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.home_grid_card_height))
    ) {
        Row {
            AsyncImage(
                model = homeMediaUI.url,
                contentDescription = null,
                placeholder = painterResource(id = R.drawable.ic_load_placeholder),
                error = painterResource(id = R.drawable.ic_load_error),
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .height(150.dp)
                    .width(dimensionResource(id = R.dimen.home_grid_card_width))
            )
            Column {
                Text(
                    text = homeMediaUI.name,
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.normal_padding))
                        .wrapContentHeight(align = Alignment.Top),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2

                )

                Text(
                    text = homeMediaUI.description,
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.normal_padding))
                        .wrapContentHeight(align = Alignment.Top),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodyMedium,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2

                )
            }
        }
    }
}
