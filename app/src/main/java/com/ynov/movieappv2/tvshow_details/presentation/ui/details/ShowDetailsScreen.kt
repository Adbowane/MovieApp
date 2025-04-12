package com.ynov.movieappv2.tvshow_details.presentation.ui.details

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ynov.movieappv2.tvshow_details.presentation.viewmodel.ShowDetailsViewModel

@Composable
fun ShowDetailsScreen(showPermalink: String) {
    val viewModel: ShowDetailsViewModel = viewModel(factory = ShowDetailsViewModelFactory(showPermalink))
    val showDetails by viewModel.showDetails.collectAsState()

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        if (showDetails == null) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            ShowDetailsContent(showDetails = showDetails!!)
        }
    }
}


class ShowDetailsViewModelFactory(private val showPermalink: String) : ViewModelProvider.Factory {
    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShowDetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ShowDetailsViewModel(showPermalink) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}