package com.syahrizal.submission2daftarfilm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.syahrizal.submission2daftarfilm.data.MovieCatalogueRepository
import com.syahrizal.submission2daftarfilm.data.source.local.entity.TVShowEntity

class TVShowsViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) :
    ViewModel() {

    fun getTvShows(): LiveData<List<TVShowEntity>> = movieCatalogueRepository.getTvShows()
}