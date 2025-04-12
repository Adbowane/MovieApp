package com.ynov.movieappv2.tvshows.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ynov.movieappv2.tvshows.data.repository.PopularShowsRepositoryImpl
import com.ynov.movieappv2.tvshows.domain.model.PopularShow
import com.ynov.movieappv2.tvshows.domain.usecase.GetPopularShowsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PopularShowsViewModel : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _shows = MutableStateFlow<List<PopularShow>>(emptyList())
    val shows: StateFlow<List<PopularShow>> = _shows

    private val repository = PopularShowsRepositoryImpl()
    private val useCase = GetPopularShowsUseCase(repository)

    init {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = useCase()
                if (result.isEmpty()) {
                    _shows.value = listOf(
                        PopularShow(
                            id = 0,
                            name = "Aucune série récupérée",
                            thumbnailUrl = "",
                            startDate = null,
                            endDate = null,
                            country = null,
                            network = null
                        )
                    )
                } else {
                    _shows.value = result
                }
            } catch (e: Exception) {
                _shows.value = listOf(
                    PopularShow(
                        id = -1,
                        name = "Erreur de chargement : ${e.message}",
                        thumbnailUrl = "",
                        startDate = null,
                        endDate = null,
                        country = null,
                        network = null
                    )
                )
            } finally {
                _isLoading.value = false
            }
        }
    }
}
