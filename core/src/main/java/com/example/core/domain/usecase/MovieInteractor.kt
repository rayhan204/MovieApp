package com.example.core.domain.usecase

import com.example.core.domain.model.Movie
import com.example.core.domain.repository.IMovieRepository

class MovieInteractor(private val repository: IMovieRepository): MovieUseCase {
    override fun getAllMovie() = repository.getAllMovie()
    override fun getFavoriteMovie() = repository.getFavoriteMovie()
    override fun setFavoriteMovie(movie: Movie, state: Boolean) = repository.setFavoriteMovie(movie, state)
}