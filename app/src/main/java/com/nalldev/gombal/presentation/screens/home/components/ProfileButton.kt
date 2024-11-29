package com.nalldev.gombal.presentation.screens.home.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nalldev.gombal.ui.theme.CustomColorTheme

@Composable
fun ProfileButton(modifier: Modifier = Modifier, onClick: () -> Unit, isDarkMode: Boolean) {
    val colorOnBackground by animateColorAsState(
        CustomColorTheme.colorOnBackground(isDarkMode),
        label = "onBackground"
    )
    Row(
        modifier = modifier.clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Outlined.AccountCircle,
            contentDescription = "about_page",
            tint = colorOnBackground
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "Afrinaldi", color = colorOnBackground, fontSize = 20.sp)
        Spacer(modifier = Modifier.width(8.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileButtonPreview() {
    ProfileButton(onClick = {}, isDarkMode = false)
}