package com.nalldev.gombal.presentation.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nalldev.gombal.R
import com.nalldev.gombal.presentation.components.IconButton
import com.nalldev.gombal.presentation.components.TextWithIcon
import com.nalldev.gombal.presentation.screens.detail.components.HtmlText
import com.nalldev.gombal.ui.theme.CustomColorTheme

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel,
    onBackClick: () -> Unit,
    onApplyClick: (String) -> Unit,
    isDarkMode : Boolean
) {

    val jobModel = viewModel.jobModel

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = CustomColorTheme.colorBackground(isDarkMode))
            .systemBarsPadding()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        modifier = modifier,
                        icon = R.drawable.ic_back,
                        isDarkMode = isDarkMode,
                        onClick = onBackClick
                    )
                    Text(
                        text = "Detail",
                        color = CustomColorTheme.colorOnBackground(isDarkMode),
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    )
                }
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Row {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = jobModel.title,
                        color = CustomColorTheme.colorOnBackground(isDarkMode),
                        fontSize = 20.sp
                    )
                    Icon(
                        modifier = modifier
                            .size(24.dp)
                            .padding(top = 4.dp)
                            .clickable(onClick = {
                                viewModel.updateBookmarkedStatus(
                                    jobModel,
                                    !viewModel.isBookmarked
                                )
                            }),
                        contentDescription = null,
                        painter = painterResource(if (viewModel.isBookmarked) R.drawable.ic_bookmarked_filled else R.drawable.ic_bookmarked),
                        tint = CustomColorTheme.colorPrimary(isDarkMode)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                TextWithIcon(imageVector = Icons.Outlined.Home, text = jobModel.companyName, isDarkMode = isDarkMode)
                TextWithIcon(imageVector = Icons.Outlined.LocationOn, text = jobModel.location, isDarkMode = isDarkMode)
            }
            item {
                Text(
                    text = "Description",
                    color = CustomColorTheme.colorOnBackground(isDarkMode),
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                HtmlText(text = jobModel.description, isDarkMode = isDarkMode)
                Spacer(modifier = Modifier.height(80.dp))
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            CustomColorTheme.colorBackground(isDarkMode),
                            CustomColorTheme.colorBackground80(isDarkMode),
                            CustomColorTheme.colorBackground20(isDarkMode)
                        ),
                        start = Offset(0f, Float.POSITIVE_INFINITY),
                        end = Offset(0f, 0f)
                    )
                )
                .align(Alignment.BottomCenter)
        ) {
            Button(
                onClick = { onApplyClick.invoke(jobModel.url) },
                colors = ButtonDefaults.buttonColors(containerColor = CustomColorTheme.colorPrimary(isDarkMode)),
                shape = RoundedCornerShape(12.dp),
                modifier = modifier
                    .height(48.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .align(Alignment.Center)
            ) {
                Text(
                    text = "Apply", fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}