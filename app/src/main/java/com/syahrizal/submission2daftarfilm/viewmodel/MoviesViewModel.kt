package com.syahrizal.submission2daftarfilm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.syahrizal.submission2daftarfilm.data.MovieCatalogueRepository
import com.syahrizal.submission2daftarfilm.data.source.local.entity.MovieEntity

class MoviesViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) :
    ViewModel() {

    fun getMovies(): LiveData<List<MovieEntity>> = movieCatalogueRepository.getMovies()
}