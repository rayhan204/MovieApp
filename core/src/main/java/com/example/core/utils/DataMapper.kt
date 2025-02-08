package com.example.core.utils

import com.example.core.data.source.local.entity.MovieEntity
import com.example.core.data.source.remote.response.ResultsItem
import com.example.core.domain.model.Movie


object DataMapper {
    fun mapResponseToEntities(input: List<ResultsItem>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id,
                title = it.title,
                overview = it.overview,
                releaseDate = it.releaseDate,
                popularity = it.popularity as? Double ?: 0.0,
                voteAverage = it.voteAverage as? Double ?: 0.0,
                voteCount = it.voteCount,
                posterPath = it.posterPath,
                genreIds = it.genreIds.joinToString(separator = ","),
                isFavorite = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesDomain(input: List<MovieEntity>): List<Movie> {
        return input.map {
            Movie(
                id = it.id,
                title = it.title,
                overview = it.overview,
                releaseDate = it.releaseDate,
                popularity = it.popularity,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
                posterPath = it.posterPath,
                genreIds = it.genreIds,
                isFavorite = it.isFavorite
            )
        }
    }

    fun mapDomainToEntity(input: Movie): MovieEntity {
        return MovieEntity(
            id = input.id,
            title = input.title,
            overview = input.overview,
            releaseDate = input.releaseDate,
            popularity = input.popularity,
            voteAverage = input.voteAverage,
            voteCount = input.voteCount,
            posterPath = input.posterPath,
            genreIds = input.genreIds,
            isFavorite = input.isFavorite
        )
    }
}