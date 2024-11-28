package com.nalldev.gombal.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nalldev.gombal.R

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun JobItem(
    modifier: Modifier = Modifier,
    onContainerClick: () -> Unit,
    onBookmarkClick: () -> Unit,
    isBookmarked: Boolean,
    jobTitle: String,
    companyName: String,
    location: String,
    tags: List<String>
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(
                color = colorResource(R.color.colorBackground), shape = RoundedCornerShape(12.dp)
            )
            .border(
                width = 1.dp,
                color = colorResource(R.color.gray),
                shape = RoundedCornerShape(12.dp)
            )
            .clickable(onClick = onContainerClick)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Image(
                modifier = modifier.size(48.dp),
                contentDescription = null,
                painter = painterResource(R.drawable.ic_kotlin)
            )
            Column(
                modifier = modifier
                    .weight(1f)
                    .padding(top = 4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = jobTitle,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = colorResource(R.color.colorOnBackground)
                )
                Text(
                    text = companyName,
                    fontSize = 12.sp,
                    color = colorResource(R.color.subtitle)
                )
            }
            Icon(
                modifier = modifier
                    .size(24.dp)
                    .padding(top = 4.dp)
                    .clickable(onClick = onBookmarkClick),
                contentDescription = null,
                painter = painterResource(if (isBookmarked) R.drawable.ic_favorites_filled else R.drawable.ic_favorites),
                tint = colorResource(R.color.colorPrimary)
            )
        }

        if (tags.isNotEmpty()) {
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                tags.forEach { tag ->
                    Text(
                        modifier = Modifier
                            .wrapContentSize()
                            .background(
                                color = colorResource(R.color.colorPrimary),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(8.dp),
                        text = tag,
                        color = Color.White,
                    )
                }
            }
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Outlined.LocationOn,
                contentDescription = null,
                modifier = Modifier.size(16.dp),
                tint = colorResource(R.color.colorOnBackground)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = location,
                fontSize = 16.sp,
                color = colorResource(R.color.colorOnBackground)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun JobItemPreview() {
    JobItem(
        onContainerClick = {},
        onBookmarkClick = {},
        isBookmarked = false,
        jobTitle = "Android Developer",
        companyName = "NallDev.Inc",
        location = "Bandung",
        tags = listOf("Remote", "Mobile Developer")
    )
}