package com.example.capstoneexpert.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.core.content.IntentCompat.getParcelableExtra
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.capstoneexpert.R
import com.example.capstoneexpert.databinding.ActivityDetailBinding
import com.example.core.domain.model.Movie
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val detailMovie = getParcelableExtra(intent, EXTRA_DATA, Movie::class.java)
        showDetailMovie(detailMovie)

    }

    @SuppressLint("SetTextI18n")
    private fun showDetailMovie(detailMovie: Movie?) {
        detailMovie?.let {
            supportActionBar?.title = detailMovie.title

            Glide.with(this)
                .load(detailMovie.posterPath)
                .into(binding.ivPoster)

            binding.tvTitle.text = it.title
            val genreMap = mapOf(
                28 to "Action", 12 to "Adventure", 16 to "Animation",
                35 to "Comedy", 80 to "Crime", 99 to "Documentary",
                18 to "Drama", 10751 to "Family", 14 to "Fantasy",
                36 to "History", 27 to "Horror", 10402 to "Music",
                9648 to "Mystery", 10749 to "Romance", 878 to "Science Fiction",
                10770 to "TV Movie", 53 to "Thriller", 10752 to "War", 37 to "Western"
            )
            val genreName = detailMovie.genreIds.split(",").mapNotNull {
                genreMap[it.trim().toInt()]
            }.joinToString(",")
            binding.tvGenre.text = "Genre: $genreName"
            binding.tvReleaseDate.text = it.releaseDate
            binding.tvRatingNumber.text = it.voteAverage.toString()
            binding.ratingBar.rating = (it.voteAverage / 2).toFloat() // Konversi dari skala 10 ke skala 5
            binding.tvVoteCount.text = "Votes: ${it.voteCount}"
            binding.tvPopularity.text = "Popularity: ${it.popularity}"
            binding.tvOverview.text = it.overview

            var isFavorite = detailMovie.isFavorite
            setStatusFavorite(isFavorite)
            binding.ivFavorite.setOnClickListener {
                isFavorite = !isFavorite
                detailViewModel.setFavoriteMovie(detailMovie, isFavorite)
                setStatusFavorite(isFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.ivFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite))
        } else {
            binding.ivFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_not_favorite))
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}