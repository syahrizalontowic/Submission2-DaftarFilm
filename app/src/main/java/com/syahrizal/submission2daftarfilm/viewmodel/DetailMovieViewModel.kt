package com.syahrizal.submission2daftarfilm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.syahrizal.submission2daftarfilm.data.MovieCatalogueRepository
import com.syahrizal.submission2daftarfilm.data.source.local.entity.DetailMovieEntity

class DetailMovieViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) :
    ViewModel() {

    private var movieId: Int? = 0

    fun setSelectedMovie(movieId: Int) {
        this.movieId = movieId
    }

    fun getDetailMovie(): LiveData<DetailMovieEntity> =
        movieId?.let { movieCatalogueRepository.getDetailMovie(it) } as LiveData<DetailMovieEntity>
}
