package com.syahrizal.submission2daftarfilm.di

import com.syahrizal.submission2daftarfilm.data.MovieCatalogueRepository
import com.syahrizal.submission2daftarfilm.data.source.remote.RemoteDataSource

object Injection {

    fun provideRepository(): MovieCatalogueRepository {
        val remoteDataSource = RemoteDataSource.getInstance()
        return MovieCatalogueRepository.getInstance(remoteDataSource)
    }
}