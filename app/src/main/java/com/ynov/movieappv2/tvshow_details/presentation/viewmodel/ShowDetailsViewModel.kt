package com.ynov.movieappv2.tvshow_details.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ynov.movieappv2.tvshow_details.data.repository.ShowDetailsRepositoryImpl
import com.ynov.movieappv2.tvshow_details.domain.model.ShowDetails
import com.ynov.movieappv2.tvshow_details.domain.usecase.GetShowDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ShowDetailsViewModel(private val showPermalink: String) : ViewModel() {

    private val _showDetails = MutableStateFlow<ShowDetails?>(null)
    val showDetails: StateFlow<ShowDetails?> = _showDetails

    private val repository = ShowDetailsRepositoryImpl()
    private val useCase = GetShowDetailsUseCase(repository)

    init {
        viewModelScope.launch {
            try {
                val result = useCase(showPermalink)
                _showDetails.value = result
            } catch (e: Exception) {
                _showDetails.value = ShowDetails(
                    id = -1,
                    name = "Erreur de chargement",
                    description = "Erreur : ${e.message}",
                    imageUrl = "",
                    startDate = "Unknown",
                    country = "Unknown",
                    network = "Unknown",
                    status = "Unknown"
                )
            }
        }
    }
}