package com.syahrizal.submission2daftarfilm.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.syahrizal.submission2daftarfilm.data.source.local.entity.DetailMovieEntity
import com.syahrizal.submission2daftarfilm.data.source.local.entity.DetailTVShowEntity
import com.syahrizal.submission2daftarfilm.data.source.local.entity.MovieEntity
import com.syahrizal.submission2daftarfilm.data.source.local.entity.TVShowEntity
import com.syahrizal.submission2daftarfilm.data.source.remote.RemoteDataSource
import com.syahrizal.submission2daftarfilm.data.source.remote.response.movie.DetailMovieResponse
import com.syahrizal.submission2daftarfilm.data.source.remote.response.tvshow.DetailTVShowResponse
import com.syahrizal.submission2daftarfilm.data.source.remote.response.movie.MoviesItemResponse
import com.syahrizal.submission2daftarfilm.data.source.remote.response.tvshow.TVShowsItemResponse
import com.syahrizal.submission2daftarfilm.helper.ListToStringHelper.movieGenreListToStringConverter
import com.syahrizal.submission2daftarfilm.helper.ListToStringHelper.movieProductionCompanyListToStringConverter
import com.syahrizal.submission2daftarfilm.helper.ListToStringHelper.tvShowGenreListToStringConverter
import com.syahrizal.submission2daftarfilm.helper.ListToStringHelper.tvShowProductionCompanyListToStringConverter
import kotlin.math.roundToInt


class FakeMovieCatalogueRepository(private val remoteDataSource: RemoteDataSource) :
    MovieCatalogueDataSource {

    override fun getMovies(): LiveData<List<MovieEntity>> {
        val movieResults = MutableLiveData<List<MovieEntity>>()
        remoteDataSource.getMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onMoviesReceived(movieResponses: List<MoviesItemResponse>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in movieResponses) {
                    val movie = with(response) {
                        MovieEntity(id, posterPath, title, overview, releaseDate)
                    }
                    movieList.add(movie)
                }
                movieResults.postValue(movieList)
            }
        })
        return movieResults
    }

    override fun getTvShows(): LiveData<List<TVShowEntity>> {
        val tvShowResults = MutableLiveData<List<TVShowEntity>>()
        remoteDataSource.getTvShows(object : RemoteDataSource.LoadTvShowsCallback {
            override fun onTvShowsReceived(tvShowResponses: List<TVShowsItemResponse>) {
                val tvShowList = ArrayList<TVShowEntity>()
                tvShowResponses.forEach {
                    val tvShow = with(it) {
                        TVShowEntity(id, posterPath, name, overview, firstAirDate)
                    }
                    tvShowList.add(tvShow)
                }
                tvShowResults.postValue(tvShowList)
            }
        })
        return tvShowResults
    }

    override fun getDetailMovie(movieId: Int): LiveData<DetailMovieEntity> {
        val detailMovieResult = MutableLiveData<DetailMovieEntity>()
        remoteDataSource.getDetailMovie(movieId, object : RemoteDataSource.LoadDetailMovieCallback {
            override fun onDetailMovieReceived(detailMovieResponse: DetailMovieResponse) {
                val detailMovieEntity = with(detailMovieResponse) {
                    DetailMovieEntity(
                        id,
                        posterPath,
                        title,
                        releaseDate,
                        movieGenreListToStringConverter(detailMovieResponse).toString(),
                        popularity,
                        (voteAverage * 10).roundToInt(),
                        tagline,
                        overview,
                        movieProductionCompanyListToStringConverter(detailMovieResponse).toString()
                    )
                }
                detailMovieResult.postValue(detailMovieEntity)
            }
        })
        return detailMovieResult
    }

    override fun getDetailTVShow(tvId: Int): LiveData<DetailTVShowEntity> {
        val detailTvShowResult = MutableLiveData<DetailTVShowEntity>()
        remoteDataSource.getDetailTVShow(tvId, object : RemoteDataSource.LoadDetailTvShowCallback {
            override fun onDetailTVShowReceived(detailTVShowResponse: DetailTVShowResponse) {
                val detailTvShowEntity = with(detailTVShowResponse) {
                    DetailTVShowEntity(
                        id,
                        posterPath,
                        name,
                        firstAirDate,
                        tvShowGenreListToStringConverter(detailTVShowResponse).toString(),
                        popularity,
                        (voteAverage * 10).roundToInt(),
                        tagline,
                        overview,
                        tvShowProductionCompanyListToStringConverter(detailTVShowResponse).toString()
                    )
                }
                detailTvShowResult.postValue(detailTvShowEntity)
            }
        })
        return detailTvShowResult
    }
}