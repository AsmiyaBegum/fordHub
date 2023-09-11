package com.ab.fordhub.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import com.ab.fordhub.R
import com.ab.fordhub.colorButton.BellColorButton
import com.ab.fordhub.colorButton.ButtonBackground
import com.ab.fordhub.colorButton.ColorButtonAnimation
import com.exyte.animatednavbar.utils.lerp
import com.exyte.animatednavbar.utils.noRippleClickable
import com.exyte.animatednavbar.utils.toPxf

import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ab.fordhub.route.Route
import com.exyte.animatednavbar.utils.lerp
import com.exyte.animatednavbar.utils.noRippleClickable
import com.exyte.animatednavbar.utils.toDp
import com.exyte.animatednavbar.utils.toPxf

@Stable
data class Item(
    @DrawableRes val icon: Int,
    var isSelected: Boolean,
    @StringRes val description: Int,
    val animationType: ColorButtonAnimation = BellColorButton(
        tween(500),
        background = ButtonBackground(R.drawable.ic_launcher_background)
    ),
    var route : String
)


val colorButtons = listOf(
    Item(
        icon = R.drawable.ic_home,
        isSelected = true,
        description = R.string.home,
        animationType = BellColorButton(
            animationSpec = spring(dampingRatio = 0.7f, stiffness = 20f),
            background = ButtonBackground(
                icon = R.drawable.circle_background,
                offset = DpOffset(2.5.dp, 3.dp)
            ),
        ),
        route = Route.Home.TestDrive.VEHICLE_LIST
    ),
    Item(
        icon = R.drawable.ic_service,
        isSelected = false,
        description = R.string.service,
        animationType = BellColorButton(
            animationSpec = spring(dampingRatio = 0.7f, stiffness = 20f),
            background = ButtonBackground(
                icon = R.drawable.circle_background,
                offset = DpOffset(1.dp, 2.dp)
            ),
        ),
        route = Route.Home.Services.SERVICE_LIST
    ),
    Item(
        icon = R.drawable.ic_centre,
        isSelected = false,
        description = R.string.ford_centers,
        animationType = BellColorButton(
            animationSpec = spring(dampingRatio = 0.7f, stiffness = 20f),
            background = ButtonBackground(
                icon = R.drawable.circle_background,
                offset = DpOffset(1.6.dp, 2.dp)
            ),
        ),
        route = Route.Home.CENTERS
    ),
    Item(
        icon = R.drawable.ic_profile,
        isSelected = false,
        description = R.string.profile,
        animationType = BellColorButton(
            animationSpec = spring(dampingRatio = 0.7f, stiffness = 20f),
            background = ButtonBackground(
                icon = R.drawable.circle_background,
                offset = DpOffset(1.dp, 1.5.dp)
            ),
        ),
        route = Route.Home.PROFILE
    )
)

@Composable
fun ColorButton(
    modifier: Modifier = Modifier,
    index: Int,
    selectedIndex: Int,
    prevSelectedIndex: Int,
    onClick: () -> Unit,
    @DrawableRes icon: Int,
    contentDescription: String? = null,
    background: ButtonBackground,
    backgroundAnimationSpec: AnimationSpec<Float> = remember { tween(300, easing = LinearEasing) },
    animationType: ColorButtonAnimation,
    maxBackgroundOffset: Dp = 25.dp
) {

    Box(
        modifier = modifier.noRippleClickable { onClick() }
    ) {
        val isSelected = remember(selectedIndex, index) { selectedIndex == index }

        val fraction = animateFloatAsState(
            targetValue = if (isSelected) 1f else 0f,
            animationSpec = backgroundAnimationSpec,
            label = "fractionAnimation",
        )

        val density = LocalDensity.current
        val maxOffset = remember(maxBackgroundOffset) { maxBackgroundOffset.toPxf(density) }

        val isFromLeft = remember(prevSelectedIndex, index, selectedIndex) {
            (prevSelectedIndex < index) || (selectedIndex > index)
        }
        val offset by remember(isSelected, isFromLeft) {
            derivedStateOf {
                calculateBackgroundOffset(
                    isSelected = isSelected,
                    isFromLeft = isFromLeft,
                    maxOffset = maxOffset,
                    fraction = fraction.value
                )
            }
        }

        Image(
            modifier = Modifier
                .offset(x = background.offset.x + offset.toDp(), y = background.offset.y)
                .scale(fraction.value)
                .align(Alignment.Center),
            painter = painterResource(id = background.icon),
            contentDescription = contentDescription
        )

        animationType.AnimatingIcon(
            modifier = Modifier.align(Alignment.Center),
            isSelected = isSelected,
            isFromLeft = isFromLeft,
            icon = icon,
        )
    }
}

private fun calculateBackgroundOffset(
    isSelected: Boolean,
    isFromLeft: Boolean,
    fraction: Float,
    maxOffset: Float
): Float {
    val offset = if (isFromLeft) -maxOffset else maxOffset
    return if (isSelected) {
        lerp(offset, 0f, fraction)
    } else {
        lerp(-offset, 0f, fraction)
    }
}