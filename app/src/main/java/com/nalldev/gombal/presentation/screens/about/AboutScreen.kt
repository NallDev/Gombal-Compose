package com.nalldev.gombal.presentation.screens.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nalldev.gombal.R
import com.nalldev.gombal.presentation.components.IconButton

@Composable
fun AboutScreen(modifier: Modifier = Modifier, onBackClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.colorBackground))
            .padding(horizontal = 16.dp)
            .systemBarsPadding(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(
                modifier = modifier,
                icon = R.drawable.ic_back,
                color = R.color.colorPrimary,
                onClick = onBackClick
            )
            Text(
                text = "About",
                color = colorResource(R.color.colorOnBackground),
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
        }

        Image(
            painter = painterResource(R.drawable.naldi_aja),
            contentDescription = "about_page",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .clip(shape = RoundedCornerShape(12.dp))
        )
        Text(
            text = "Naldi Aja",
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            color = colorResource(R.color.colorOnBackground)
        )
        Text(
            text = "afrinaldi.genz0@gmail.com",
            fontSize = 12.sp,
            color = colorResource(R.color.colorOnBackground)
        )
    }
}