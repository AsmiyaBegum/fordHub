package com.ab.fordhub.testdrive

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import com.ab.fordhub.R
import com.ab.fordhub.common.FordImage
import com.ab.fordhub.common.GradientButton
import com.ab.fordhub.model.VehicleDetail
import com.ab.fordhub.ui.theme.darkIndigo
import com.ab.fordhub.ui.theme.midnightBlue
import com.ab.fordhub.ui.theme.royalBlue
import com.ab.fordhub.util.Utils
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.sp
import com.ab.fordhub.common.FordDivider
import kotlin.math.max
import kotlin.math.min
import androidx.compose.ui.util.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavOptions
import com.ab.fordhub.route.Route
import com.ab.fordnavigation.NavInfo


private val BottomBarHeight = 56.dp
private val TitleHeight = 128.dp
private val GradientScroll = 180.dp
private val ImageOverlap = 115.dp
private val MinTitleOffset = 56.dp
private val MinImageOffset = 12.dp
private val MaxTitleOffset = ImageOverlap + MinTitleOffset + GradientScroll
private val ExpandedImageSize = 300.dp
private val CollapsedImageSize = 40.dp
private val HzPadding = Modifier.padding(horizontal = 24.dp)



@Preview
@Composable
fun Vehicle(){
//    VehicleDetail(VehicleDetail("Ford Fusion","Midsize Sedan","Type: Midsize Sedan\\nEngine Options: Gasoline, Hybrid\\nFuel Efficiency: Varies by engine","Comfortable ride, spacious interior with quality materials, advanced tech options including Ford Co-Pilot360, available hybrid model with excellent fuel efficiency",
//    "https://drive.google.com/uc?export=download&id=1sMGQwNngedzjO9mCCNVDeO38ImVRmDjd")){
//    }
}


@Composable
fun VehicleDetailScreen(
    vehicleDetail: VehicleDetail,
    viewModel : TestDriveCarDetailViewModel = hiltViewModel()
) {
    val snack = remember(vehicleDetail) { vehicleDetail }

    Box(
        Modifier
            .fillMaxSize()) {
        val scroll = rememberScrollState(0)
        Header()
        Body(snack, scroll)
        Title(snack) { scroll.value }
        DetailImage(snack.url) { scroll.value }
        GradientButton(text = "Book a test drive",textColor =  Color.White, gradient = Brush.linearGradient(
            listOf(midnightBlue, royalBlue, darkIndigo)), modifier = Modifier.align(Alignment.BottomCenter),
        onClick = {
            viewModel.navManager.navigate(
                NavInfo(
                    id = "${Route.Home.TestDrive.TESTDRIVE_BOOKING}/$vehicleDetail",
                    navOption = NavOptions
                        .Builder()
                        .setPopUpTo(Route.Home.TestDrive.VEHICLE_LIST, inclusive = true)
                        .build()
                )
            )
        })
    }
}

@Composable
private fun Header() {
    Spacer(
        modifier = Modifier
            .height(280.dp)
            .fillMaxWidth()
            .background(Brush.horizontalGradient(listOf(midnightBlue, royalBlue, darkIndigo)))
    )
}

@Composable
private fun Up(upPress: () -> Unit) {
    IconButton(
        onClick = upPress,
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .size(36.dp)
            .background(
                color = MaterialTheme.colors.primary.copy(alpha = 0.32f),
                shape = CircleShape
            )
    ) {
        Icon(
            imageVector = Utils.mirroringBackIcon(),
            tint = royalBlue,
            contentDescription = stringResource(R.string.label_back)
        )
    }
}

@Composable
private fun Body(
    related: VehicleDetail,
    scroll: ScrollState
) {
    Column {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .height(MinTitleOffset)
        )
        Column(
            modifier = Modifier.verticalScroll(scroll)
        ) {
            Spacer(Modifier.height(GradientScroll))
            Surface(Modifier.fillMaxWidth()) {
                Column {
                    Spacer(Modifier.height(ImageOverlap))
                    Spacer(Modifier.height(TitleHeight))

                    Spacer(Modifier.height(16.dp))
                    Text(
                        text = stringResource(R.string.detail_header),
                        style = MaterialTheme.typography.subtitle1,
                        color = MaterialTheme.colors.primary,
                        modifier = HzPadding
                    )
                    Spacer(Modifier.height(16.dp))

                    Text(
                        text = related.description,
                        style = MaterialTheme.typography.button,
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colors.primaryVariant,
                        modifier = Modifier
                            .heightIn(20.dp)
                            .fillMaxWidth()
                            .padding(top = 5.dp)
                            .padding(horizontal = 24.dp)
                    )
                    Spacer(Modifier.height(20.dp))
                    Text(
                        text = stringResource(R.string.features),
                        style = MaterialTheme.typography.subtitle1,
                        color = MaterialTheme.colors.primary,
                        modifier = HzPadding
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = related.feature,
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.primaryVariant,
                        modifier = HzPadding
                            .padding(top = 5.dp)
                    )

                    Spacer(Modifier.height(16.dp))

                    Text(
                        text = stringResource(R.string.specification),
                        style = MaterialTheme.typography.subtitle1,
                        color = MaterialTheme.colors.primary,
                        modifier = HzPadding
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = related.specification,
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.primaryVariant,
                        modifier = HzPadding
                            .padding(top = 5.dp)
                    )

                    Spacer(
                        modifier = Modifier
                            .padding(bottom = BottomBarHeight)
                            .navigationBarsPadding()
                            .height(10.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun Title(snack: VehicleDetail, scrollProvider: () -> Int) {
    val maxOffset = with(LocalDensity.current) { MaxTitleOffset.toPx() }
    val minOffset = with(LocalDensity.current) { MinTitleOffset.toPx() }

    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .heightIn(min = TitleHeight)
            .statusBarsPadding()
            .offset {
                val scroll = scrollProvider()
                val offset = (maxOffset - scroll).coerceAtLeast(minOffset)
                IntOffset(x = 0, y = offset.toInt())
            }
            .background(color = MaterialTheme.colors.background)
    ) {
        Spacer(Modifier.height(16.dp))
        Text(
            text = snack.name,
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.primary,
            modifier = HzPadding
        )
        Text(
            text = snack.name,
            style = MaterialTheme.typography.subtitle2,
            fontSize = 20.sp,
            color = MaterialTheme.colors.secondaryVariant,
            modifier = HzPadding
        )
        Spacer(Modifier.height(4.dp))


        FordDivider()
    }
}

@Composable
private fun DetailImage(
    imageUrl: String,
    scrollProvider: () -> Int
) {
    val collapseRange = with(LocalDensity.current) { (MaxTitleOffset - MinTitleOffset).toPx() }
    val collapseFractionProvider = {
        (scrollProvider() / collapseRange).coerceIn(0f, 1f)
    }

    CollapsingImageLayout(
        collapseFractionProvider = collapseFractionProvider,
        modifier = HzPadding.then(Modifier.statusBarsPadding())
    ) {
        FordImage(
            imageUrl = imageUrl,
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun CollapsingImageLayout(
    collapseFractionProvider: () -> Float,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        check(measurables.size == 1)

        val collapseFraction = collapseFractionProvider()

        val imageMaxSize = min(ExpandedImageSize.roundToPx(), constraints.maxWidth)
        val imageMinSize = max(CollapsedImageSize.roundToPx(), constraints.minWidth)
        val imageWidth = lerp(imageMaxSize, imageMinSize, collapseFraction)
        val imagePlaceable = measurables[0].measure(Constraints.fixed(imageWidth, imageWidth))

        val imageY = lerp(MinTitleOffset, MinImageOffset, collapseFraction).roundToPx()
        val imageX = lerp(
            (constraints.maxWidth - imageWidth) / 2, // centered when expanded
            constraints.maxWidth - imageWidth, // right aligned when collapsed
            collapseFraction
        )
        layout(
            width = constraints.maxWidth,
            height = imageY + imageWidth
        ) {
            imagePlaceable.placeRelative(imageX, imageY)
        }
    }
}

