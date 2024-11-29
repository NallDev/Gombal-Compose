package com.nalldev.gombal.presentation.screens.home

import android.widget.Toast
import androidx.compose.animation.animateColorAsState
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nalldev.gombal.R
import com.nalldev.gombal.domain.model.JobModel
import com.nalldev.gombal.presentation.components.IconButton
import com.nalldev.gombal.presentation.components.JobItem
import com.nalldev.gombal.presentation.screens.home.components.ProfileButton
import com.nalldev.gombal.presentation.screens.home.components.SearchTextField
import com.nalldev.gombal.presentation.screens.home.components.ThemeToggleButton
import com.nalldev.gombal.ui.theme.CustomColorTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    isDarkMode: Boolean,
    navigateToDetail: (JobModel) -> Unit,
    navigateToProfile: () -> Unit,
    navigateToBookmark: () -> Unit
) {
    val context = LocalContext.current
    val isLoading by viewModel.loading.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val jobs = viewModel.jobs
    val toastEvent = viewModel.toastEvent

    val colorBackground by animateColorAsState(
        CustomColorTheme.colorBackground(isDarkMode),
        label = "background"
    )
    val colorPrimary by animateColorAsState(
        CustomColorTheme.colorPrimary(isDarkMode),
        label = "primary"
    )
    val colorOnBackground by animateColorAsState(
        CustomColorTheme.colorOnBackground(isDarkMode),
        label = "onBackground"
    )

    LaunchedEffect(toastEvent) {
        toastEvent?.let { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            viewModel.clearToastEvent()
        }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(color = colorBackground)
            .padding(horizontal = 16.dp)
            .systemBarsPadding(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Spacer(modifier = modifier.height(16.dp))
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ProfileButton(onClick = navigateToProfile, isDarkMode = isDarkMode)
                ThemeToggleButton(
                    onClick = {
                        viewModel.setDarkMode(!isDarkMode)
                    },
                    isChecked = isDarkMode
                )
            }
            Spacer(modifier = modifier.height(16.dp))
        }
        item {
            Row {
                SearchTextField(
                    modifier = modifier.weight(1f),
                    onValueChange = { query -> viewModel.updateSearchQuery(query) },
                    value = searchQuery,
                    isDarkMode = isDarkMode
                )
                Spacer(modifier = modifier.width(8.dp))
                IconButton(
                    icon = R.drawable.ic_bookmarked,
                    isDarkMode = isDarkMode,
                    onClick = navigateToBookmark
                )
            }
        }
        if (isLoading) {
            item {
                Column(
                    modifier = modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(color = colorPrimary)
                }
            }
        } else {
            if (jobs.isEmpty()) {
                item {
                    Column(
                        modifier = modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "No Jobs Found",
                            color = colorOnBackground
                        )
                    }
                }
            } else {
                items(jobs, key = { it.id }) {
                    JobItem(
                        modifier = modifier.animateItem(),
                        onContainerClick = {
                            navigateToDetail.invoke(it)
                        },
                        onBookmarkClick = {
                            viewModel.updateBookmarkedStatus(it, !it.isBookmarked)
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
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        navigateToDetail = {},
        navigateToProfile = {},
        navigateToBookmark = {},
        viewModel = koinViewModel(),
        isDarkMode = false
    )
}
