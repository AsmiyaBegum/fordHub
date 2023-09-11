package com.ab.fordhub.model

import androidx.compose.ui.graphics.Color
import com.ab.fordhub.ui.theme.md_theme_light_primary

data class OnBoardingData(
    val image: Int, val title: String,
    val desc: String,
    val backgroundColor: Color,
    val mainColor: Color = md_theme_light_primary
)
