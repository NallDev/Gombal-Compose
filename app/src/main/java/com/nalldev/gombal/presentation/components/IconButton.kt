package com.nalldev.gombal.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nalldev.gombal.R
import com.nalldev.gombal.ui.theme.CustomColorTheme

@Composable
fun IconButton(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    isDarkMode: Boolean,
    onClick: () -> Unit
) {
    val colorPrimary by animateColorAsState(
        CustomColorTheme.colorPrimary(isDarkMode),
        label = "primary"
    )
    val colorBackground by animateColorAsState(
        CustomColorTheme.colorBackground(isDarkMode),
        label = "background"
    )
    val gray by animateColorAsState(
        CustomColorTheme.gray(isDarkMode),
        label = "gray"
    )
    ElevatedButton(
        onClick = onClick,
        modifier = modifier
            .size(48.dp)
            .border(
                width = 1.dp,
                color = gray,
                shape = RoundedCornerShape(12.dp)
            ),
        contentPadding = PaddingValues(0.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.elevatedButtonColors()
            .copy(containerColor = colorBackground),
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            tint = colorPrimary,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun IconButtonPreview() {
    IconButton(icon = R.drawable.ic_bookmarked, isDarkMode = false, onClick = {})
}