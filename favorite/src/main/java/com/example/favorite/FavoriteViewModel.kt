package com.example.favorite

import com.example.core.domain.usecase.MovieUseCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData


class FavoriteViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val favoriteMovie = movieUseCase.getFavoriteMovie().asLiveData()
}