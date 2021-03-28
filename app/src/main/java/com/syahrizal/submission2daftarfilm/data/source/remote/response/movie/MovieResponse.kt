package com.syahrizal.submission2daftarfilm.data.source.remote.response.movie

import com.google.gson.annotations.SerializedName

data class MovieResponse(

    @field:SerializedName("results")
    val results: List<MoviesItemResponse>
)