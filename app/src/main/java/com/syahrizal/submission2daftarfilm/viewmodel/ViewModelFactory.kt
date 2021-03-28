package com.syahrizal.submission2daftarfilm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.syahrizal.submission2daftarfilm.data.MovieCatalogueRepository
import com.syahrizal.submission2daftarfilm.di.Injection.provideRepository

class ViewModelFactory private constructor(
    private val mMovieCatalogueRepository:
    MovieCatalogueRepository
) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(provideRepository())
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MoviesViewModel::class.java) -> {
                MoviesViewModel(mMovieCatalogueRepository) as T
            }
            modelClass.isAssignableFrom(TVShowsViewModel::class.java) -> {
                TVShowsViewModel(mMovieCatalogueRepository) as T
            }
            modelClass.isAssignableFrom(DetailMovieViewModel::class.java) -> {
                DetailMovieViewModel(mMovieCatalogueRepository) as T
            }
            modelClass.isAssignableFrom(DetailTVShowViewModel::class.java) -> {
                DetailTVShowViewModel(mMovieCatalogueRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}