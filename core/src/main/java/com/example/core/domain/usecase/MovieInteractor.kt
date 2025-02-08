package com.example.core.domain.usecase

import com.example.core.domain.model.Movie
import com.example.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val repository: IMovieRepository): MovieUseCase {
    override fun getAllMovie() = repository.getAllMovie()
    override fun getFavoriteMovie() = repository.getFavoriteMovie()
    override fun setFavoriteMovie(movie: Movie, state: Boolean) = repository.setFavoriteMovie(movie, state)
    override fun searchMovie(query: String): Flow<List<Movie>> =
        repository.searchMovie(query)
}