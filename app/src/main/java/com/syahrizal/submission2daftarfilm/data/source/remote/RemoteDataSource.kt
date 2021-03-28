package com.syahrizal.submission2daftarfilm.data.source.remote

import android.util.Log
import com.syahrizal.submission2daftarfilm.BuildConfig
import com.syahrizal.submission2daftarfilm.data.source.remote.response.movie.DetailMovieResponse
import com.syahrizal.submission2daftarfilm.data.source.remote.response.movie.MovieResponse
import com.syahrizal.submission2daftarfilm.data.source.remote.response.movie.MoviesItemResponse
import com.syahrizal.submission2daftarfilm.data.source.remote.response.tvshow.DetailTVShowResponse
import com.syahrizal.submission2daftarfilm.data.source.remote.response.tvshow.TVShowResponse
import com.syahrizal.submission2daftarfilm.data.source.remote.response.tvshow.TVShowsItemResponse
import com.syahrizal.submission2daftarfilm.network.ApiConfig.getApiService
import com.syahrizal.submission2daftarfilm.utils.EspressoIdlingResource.decrement
import com.syahrizal.submission2daftarfilm.utils.EspressoIdlingResource.increment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource = instance ?: synchronized(this) {
            instance ?: RemoteDataSource()
        }

        private const val MOVIE_PATH = "movie"
        private const val TV_SHOW_PATH = "tv"
        private const val API_KEY = BuildConfig.API_KEY
        private const val LANGUAGE_QUERY = "en-US"
        private const val SORT_QUERY = "popularity.desc"
        private const val IS_ADULT_QUERY = false
    }
    private val TAG = RemoteDataSource::class.java.simpleName

    fun getMovies(callback: LoadMoviesCallback) {
        increment()
        val client = getApiService()
            .getMovies(MOVIE_PATH, API_KEY, LANGUAGE_QUERY, SORT_QUERY, IS_ADULT_QUERY)
        client.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>)
            {
                if (response.isSuccessful)
                    response.body()?.results?.let { callback.onMoviesReceived(it) }
                else Log.e(TAG, response.message())
                decrement()
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e(TAG, "${t.message}")
                decrement()
            }
        })
    }

    interface LoadMoviesCallback {
        fun onMoviesReceived(movieResponses: List<MoviesItemResponse>)
    }

    fun getTvShows(callback: LoadTvShowsCallback) {
        increment()
        val client = getApiService()
            .getTvShows(TV_SHOW_PATH, API_KEY, LANGUAGE_QUERY, SORT_QUERY)
        client.enqueue(object : Callback<TVShowResponse> {
            override fun onResponse(
                call: Call<TVShowResponse>,
                response: Response<TVShowResponse>)
            {
                if (response.isSuccessful)
                    response.body()?.results?.let { callback.onTvShowsReceived(it) }
                else Log.e(TAG, response.message())
                decrement()
            }

            override fun onFailure(call: Call<TVShowResponse>, t: Throwable) {
                Log.d(TAG, t.message.toString())
                decrement()
            }
        })
    }

    interface LoadTvShowsCallback {
        fun onTvShowsReceived(tvShowResponses: List<TVShowsItemResponse>)
    }

    fun getDetailMovie(movieId: Int, callback: LoadDetailMovieCallback) {
        increment()
        val client = getApiService().getDetailMovie(movieId, API_KEY, LANGUAGE_QUERY)
        client.enqueue(object : Callback<DetailMovieResponse> {
            override fun onResponse(
                call: Call<DetailMovieResponse>,
                response: Response<DetailMovieResponse>)
            {
                if (response.isSuccessful)
                    response.body()?.let { callback.onDetailMovieReceived(it) }
                else Log.e(TAG, response.message())
                decrement()
            }

            override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
                Log.e(TAG, t.message.toString())
                decrement()
            }
        })
    }

    interface LoadDetailMovieCallback {
        fun onDetailMovieReceived(detailMovieResponse: DetailMovieResponse)
    }

    fun getDetailTVShow(tvId: Int, callback: LoadDetailTvShowCallback) {
        increment()
        val client = getApiService().getDetailTVShow(tvId, API_KEY, LANGUAGE_QUERY)
        client.enqueue(object : Callback<DetailTVShowResponse> {
            override fun onResponse(
                call: Call<DetailTVShowResponse>,
                response: Response<DetailTVShowResponse>)
            {
                if (response.isSuccessful)
                    callback.onDetailTVShowReceived(response.body() as DetailTVShowResponse)
                else Log.e(TAG, response.message())
                decrement()
            }

            override fun onFailure(call: Call<DetailTVShowResponse>, t: Throwable) {
                Log.e(TAG, "${t.message}")
                decrement()
            }
        })
    }

    interface LoadDetailTvShowCallback {
        fun onDetailTVShowReceived(detailTVShowResponse: DetailTVShowResponse)
    }
}