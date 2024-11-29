package com.nalldev.gombal.presentation.screens.bookmark

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nalldev.gombal.R
import com.nalldev.gombal.domain.model.JobModel
import com.nalldev.gombal.presentation.components.IconButton
import com.nalldev.gombal.presentation.components.JobItem
import com.nalldev.gombal.ui.theme.CustomColorTheme

@Composable
fun BookmarkScreen(
    modifier: Modifier = Modifier,
    viewModel: BookmarkViewModel,
    onBackClick: () -> Unit,
    navigateToDetail: (JobModel) -> Unit,
    isDarkMode: Boolean
) {
    val bookmarkedJobs by viewModel.bookmarkedJobs.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val toastEvent = viewModel.toastEvent

    LaunchedEffect(toastEvent) {
        toastEvent?.let { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            viewModel.clearToastEvent()
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = CustomColorTheme.colorBackground(isDarkMode))
            .padding(horizontal = 16.dp)
            .systemBarsPadding(),
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
                    text = "Bookmarked",
                    color = CustomColorTheme.colorOnBackground(isDarkMode),
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )
            }
        }
        if (bookmarkedJobs.isEmpty()) {
            item {
                Column(
                    modifier = modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "There is no bookmarked job",
                        color = CustomColorTheme.colorOnBackground(isDarkMode)
                    )
                }
            }
        } else {
            items(bookmarkedJobs, key = { it.id }) {
                JobItem(
                    modifier = modifier.animateItem(),
                    onContainerClick = {
                        navigateToDetail.invoke(it)
                    },
                    onBookmarkClick = {
                        viewModel.deleteFromBookmarked(it)
                    },
                    isBookmarked = it.isBookmarked,
                    jobTitle = it.title,
                    companyName = it.companyName,
                    location = it.location,
                    tags = it.tags,
                    isDarkMode = isDarkMode
                )
            }
        }

    }
}