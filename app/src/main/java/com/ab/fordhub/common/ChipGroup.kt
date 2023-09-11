package com.ab.fordhub.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ab.fordhub.util.Constants

@Preview(showBackground = true)
@Composable
fun ChipGroup(
    strings: List<String> = Constants.VEHICLE_LIST,
    selectedCar: String? = null,
    onSelectedChanged: (String) -> Unit = {},
) {
    Column(modifier = Modifier.padding(8.dp)) {
        LazyRow {
            items(strings) {
                Chip(
                    name = it,
                    isSelected = selectedCar == it,
                    onSelectionChanged = {
                        onSelectedChanged(it)
                    },
                )
                Spacer(modifier = Modifier.width(15.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Chip(
    modifier: Modifier = Modifier,
    onSelectionChanged: (String) -> Unit = {},
    name: String = "Chip",
    isSelected: Boolean = true,
    content: @Composable () -> Unit = {Text(
        text = name,
        color = Color.White,
        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
    )}
) {
    Surface(
        modifier = modifier.padding(4.dp),
        elevation = 8.dp,
        shape = MaterialTheme.shapes.medium,
        color = if (isSelected) MaterialTheme.colors.primary else Color.LightGray
    ) {
        Row(modifier = Modifier
            .toggleable(
                value = isSelected,
                onValueChange = {
                    onSelectionChanged(name)
                }
            )
        ) {
            content()
        }
    }
}