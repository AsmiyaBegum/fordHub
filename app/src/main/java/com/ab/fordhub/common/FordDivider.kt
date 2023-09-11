package com.ab.fordhub.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ab.fordhub.ui.theme.FordHubTheme

@Composable
fun FordDivider(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.primary.copy(alpha = DividerAlpha),
    thickness: Dp = 1.dp,
    startIndent: Dp = 0.dp
) {
    Divider(
        modifier = modifier,
        color = color,
        thickness = thickness,
        startIndent = startIndent
    )
}

private const val DividerAlpha = 0.12f

@Preview("default", showBackground = true)
@Composable
private fun DividerPreview() {
    FordHubTheme {
        Box(Modifier.size(height = 10.dp, width = 100.dp)) {
            FordDivider(Modifier.align(Alignment.Center))
        }
    }
}