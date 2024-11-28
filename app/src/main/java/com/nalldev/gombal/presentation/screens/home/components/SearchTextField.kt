package com.nalldev.gombal.presentation.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nalldev.gombal.R

@Composable
fun SearchTextField(modifier: Modifier = Modifier, onValueChange: (String) -> Unit, value: String) {
    BasicTextField(
        modifier = modifier
            .height(48.dp)
            .fillMaxWidth()
            .background(
                color = colorResource(R.color.colorBackground),
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                width = 1.dp,
                color = colorResource(R.color.gray),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 16.dp),
        textStyle = TextStyle.Default.copy(fontSize = 12.sp, color = colorResource(R.color.colorOnBackground)),
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        decorationBox = { innerTextField ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = null,
                    tint = colorResource(R.color.subtitle)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Box(contentAlignment = Alignment.CenterStart,) {
                    if (value.isEmpty()) {
                        Text(
                            text = "Search job",
                            color = colorResource(R.color.subtitle),
                            fontSize = 12.sp
                        )
                    }
                    innerTextField()
                }
            }
        }
    )
}

@Preview
@Composable
fun SearchTextFieldPreview() {
    SearchTextField(onValueChange = {}, value = "")
}