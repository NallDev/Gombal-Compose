package com.nalldev.gombal.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
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
        ConstraintLayout(modifier = modifier.fillMaxWidth()) {
            val (image, column, icon) = createRefs()

            Image(
                painter = painterResource(R.drawable.ic_kotlin),
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp)
                    .constrainAs(image) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                    }
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier
                    .constrainAs(column) {
                        start.linkTo(image.end, margin = 8.dp)
                        top.linkTo(parent.top, margin = 4.dp)
                        end.linkTo(icon.start, margin = 8.dp)
                        width = androidx.constraintlayout.compose.Dimension.fillToConstraints
                    }
            ) {
                Text(
                    text = jobTitle,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = colorResource(R.color.colorOnBackground),
                )
                Text(
                    text = companyName,
                    fontSize = 12.sp,
                    color = colorResource(R.color.subtitle),
                )
            }

            Icon(
                painter = painterResource(if (isBookmarked) R.drawable.ic_bookmarked_filled else R.drawable.ic_bookmarked),
                contentDescription = null,
                tint = colorResource(R.color.colorPrimary),
                modifier = Modifier
                    .size(24.dp)
                    .padding(top = 4.dp)
                    .clickable(onClick = onBookmarkClick)
                    .constrainAs(icon) {
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                    }
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

        TextWithIcon(
            modifier = modifier.fillMaxWidth(),
            imageVector = Icons.Outlined.LocationOn,
            text = location
        )
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