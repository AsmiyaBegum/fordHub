package com.ab.fordhub.common

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ab.fordhub.ui.theme.md_theme_dark_onPrimary
import com.ab.fordhub.ui.theme.md_theme_dark_primary
import com.ab.fordhub.ui.theme.md_theme_dark_surfaceTint
import com.ab.fordhub.ui.theme.midnightBlue


@Composable
fun SearchBox( text: String,
               onTextChange: (String) -> Unit,
               onCloseClicked: () -> Unit,
               onSearchClicked: (String) -> Unit) {
    val coffeeCupIcon = Icons.Default.Search
    val hintText = "Find Vehicle"


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(
                    color = Color(0xFF7FBBCE),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(vertical = 2.dp, horizontal = 16.dp)
        ) {
            Icon(
                imageVector = coffeeCupIcon,
                contentDescription = "Search",
                tint = midnightBlue,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.CenterStart
            ) {
                TextField(
                    value = text,
                    onValueChange = {
                        onTextChange(it)
                    } ,
                    textStyle = MaterialTheme.typography.subtitle1.copy(color = midnightBlue),
                    singleLine = true,
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                if (text.isNotEmpty()) {
                                    onTextChange("")
                                } else {
                                    onCloseClicked()
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close Icon",
                                tint = Color.White
                            )
                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            onSearchClicked(text)
                        }
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent, // Remove the background color
                        focusedIndicatorColor = Color.Transparent, // Remove the underline when focused
                        unfocusedIndicatorColor = Color.Transparent, // Remove the underline when not focused
                        disabledIndicatorColor = Color.Transparent // Remove the underline when disabled
                    ))

                if (text.isEmpty()) {
                    Text(
                        text = hintText,
                        color = Color.Gray,
                        style = MaterialTheme.typography.subtitle1,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.fillMaxWidth().height(8.dp)) // Add space at the end of the SearchBox
    }
}