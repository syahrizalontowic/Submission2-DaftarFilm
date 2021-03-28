package com.syahrizal.submission2daftarfilm.data

import androidx.lifecycle.LiveData
import com.syahrizal.submission2daftarfilm.data.source.local.entity.DetailMovieEntity
import com.syahrizal.submission2daftarfilm.data.source.local.entity.DetailTVShowEntity
import com.syahrizal.submission2daftarfilm.data.source.local.entity.MovieEntity
import com.syahrizal.submission2daftarfilm.data.source.local.entity.TVShowEntity

interface MovieCatalogueDataSource {

    fun getMovies(): LiveData<List<MovieEntity>>

    fun getTvShows(): LiveData<List<TVShowEntity>>

    fun getDetailMovie(movieId: Int): LiveData<DetailMovieEntity>

    fun getDetailTVShow(tvId: Int): LiveData<DetailTVShowEntity>

}
