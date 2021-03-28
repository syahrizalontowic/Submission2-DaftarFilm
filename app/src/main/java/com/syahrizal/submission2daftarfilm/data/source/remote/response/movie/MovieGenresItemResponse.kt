package com.syahrizal.submission2daftarfilm.data.source.remote.response.movie

import com.google.gson.annotations.SerializedName

data class MovieGenresItemResponse(

    @field:SerializedName("name")
    val name: String
)
