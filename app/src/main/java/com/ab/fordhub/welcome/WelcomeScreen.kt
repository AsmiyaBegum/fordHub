package com.ab.fordhub.welcome

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.FloatRange
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavOptions
import com.ab.fordhub.R
import com.ab.fordhub.model.OnBoardingData
import com.ab.fordhub.route.Root
import com.ab.fordhub.route.Route
import com.ab.fordhub.ui.theme.BottomCardShape
import com.ab.fordnavigation.NavInfo
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

import com.google.accompanist.pager.PagerState




@OptIn(ExperimentalFoundationApi::class)
@Preview
@DelicateCoroutinesApi
@ExperimentalPagerApi
@Composable
fun OnBoardingPager(viewModel: OnBoardingViewModel = hiltViewModel()) {

    val modifier = Modifier
        .fillMaxWidth()
        .background(color = Color.Blue)
    val item = ArrayList<OnBoardingData>()

    item.add(
        OnBoardingData(
            R.drawable.ford_1,
            "Explore Diverse Cars",
            "Choose from a diverse fleet of cars, from sleek sedans to rugged SUVs, for your test drive.",
            backgroundColor = Color(0xFFFFFFFF),
            mainColor = Color(0xFF059BC7)
        )
    )

    item.add(
        OnBoardingData(
            R.drawable.ford_2,
            "Instant Slot Booking",
            "Book your test drive instantly, selecting the date and time that suits you best.",
            backgroundColor = Color(0xFF9B6DCF),
            mainColor = Color(0xFF8131DB)
        )
    )

    item.add(
        OnBoardingData(
            R.drawable.ford_3,
            "Comprehensive Car Services",
            "Beyond test drives, access a range of car services, from maintenance to detailing, all in one app.",
            backgroundColor = Color(0xFF96E172),
            mainColor = Color(0xFF0CEE3C)
        )
    )


    val pagerState = rememberPagerState(
        pageCount = item.size,
        initialOffscreenLimit = 2,
        infiniteLoop = false,
        initialPage = 0,
    )

    Box(modifier = modifier) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            HorizontalPager(count=3,state = pagerState) { page ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(item[page].backgroundColor),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {

                    Image(
                        painter = painterResource(id = item[page].image),
                        contentDescription = item[page].title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(5.dp))
                    )


                }
            }

        }

        Box(modifier = Modifier.align(Alignment.BottomCenter)) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(340.dp),
                backgroundColor = Color.White,
                elevation = 0.dp,
                shape = BottomCardShape.large
            ) {
                Box() {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        PagerIndicator(items = item, currentPage = pagerState.currentPage)
                        Text(
                            text = item[pagerState.currentPage].title,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 20.dp, end = 30.dp),
//                            color = Color(0xFF292D32),
                            color = item[pagerState.currentPage].mainColor,
//                            fontFamily = RobotoMedium,
                            textAlign = TextAlign.Right,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold
                        )

                        Text(
                            text = item[pagerState.currentPage].desc,
                            modifier = Modifier.padding(top = 20.dp, start = 40.dp, end = 20.dp),
                            color = Color.Gray,
//                            fontFamily = RobotoMedium,
                            fontSize = 17.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.ExtraLight
                        )

                    }
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(30.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {


                            if (pagerState.currentPage != 2) {
                                TextButton(onClick = {
                                    viewModel.navManager.navigate(
                                        NavInfo(id = Route.Welcome.LOGIN,
                                        navOption = NavOptions.Builder().setPopUpTo(Route.Welcome.SPLASH, inclusive = true).build())
                                    )
                                    //skip
                                }) {
                                    Text(
                                        text = "Skip Now",
                                        color = Color(0xFF292D32),
//                                        fontFamily = RobotoMedium,
                                        textAlign = TextAlign.Right,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }

                                OutlinedButton(
                                    onClick = {
                                        GlobalScope.launch {
                                            pagerState.scrollToPage(
                                                pagerState.currentPage + 1,
                                                pageOffset = 0f
                                            )
                                        }
                                    },
                                    border = BorderStroke(
                                        14.dp,
                                        item[pagerState.currentPage].mainColor
                                    ),
                                    shape = RoundedCornerShape(50), // = 50% percent
                                    //or shape = CircleShape
                                    colors = ButtonDefaults.outlinedButtonColors(contentColor = item[pagerState.currentPage].mainColor),
                                    modifier = Modifier.size(65.dp)
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_next),
                                        contentDescription = "",
                                        tint = item[pagerState.currentPage].mainColor,
                                        modifier = Modifier.size(30.dp)
                                    )
                                }
                            } else {
                                Button(
                                    onClick = {
                                        viewModel.navManager.navigate(
                                            NavInfo(id = Route.Welcome.LOGIN,
                                                navOption = NavOptions.Builder().setPopUpTo(Route.Welcome.SPLASH, inclusive = true).build())
                                        )
                                        //show home screen
                                    },
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = item[pagerState.currentPage].mainColor
                                    ),
                                    contentPadding = PaddingValues(vertical = 12.dp),
                                    elevation = ButtonDefaults.elevation(
                                        defaultElevation = 0.dp
                                    )
                                ) {
                                    Text(
                                        text = "Get Started",
                                        color = Color.White,
                                        fontSize = 16.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }

        }
    }
}

@Composable
fun PagerIndicator(currentPage: Int, items: List<OnBoardingData>) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.padding(top = 20.dp)
    ) {
        repeat(items.size) {
            Indicator(isSelected = it == currentPage, color = items[it].mainColor)
        }
    }
}

@Composable
fun Indicator(isSelected: Boolean, color: Color) {
    val width = animateDpAsState(targetValue = if (isSelected) 40.dp else 10.dp)

    Box(
        modifier = Modifier
            .padding(4.dp)
            .height(10.dp)
            .width(width.value)
            .clip(CircleShape)
            .background(
                if (isSelected) color else Color.Gray.copy(alpha = 0.5f)
            )
    )
}

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalPagerApi
@Composable
fun rememberPagerState(
    @androidx.annotation.IntRange(from = 0) pageCount: Int,
    @androidx.annotation.IntRange(from = 0) initialPage: Int = 0,
    @FloatRange(from = 0.0, to = 1.0) initialPageOffset: Float = 0f,
    @androidx.annotation.IntRange(from = 1) initialOffscreenLimit: Int = 1,
    infiniteLoop: Boolean = false
): PagerState = rememberSaveable(saver = PagerState.Saver) {
    PagerState(
        currentPage = initialPage,
    )
}