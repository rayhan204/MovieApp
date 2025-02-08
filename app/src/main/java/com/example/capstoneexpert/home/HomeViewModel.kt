package com.example.capstoneexpert.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.data.Resource
import com.example.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.flow.map

class HomeViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    val movie = movieUseCase.getAllMovie().asLiveData()

    fun searchMovie(query: String) = movieUseCase.searchMovie(query)
        .map { Resource.Success(it) }
        .asLiveData() }