package com.syahrizal.submission2daftarfilm.ui.callback

import com.syahrizal.submission2daftarfilm.data.source.local.entity.MovieEntity


interface OnMovieItemClickCallback {
    fun onItemClicked(data: MovieEntity)
    fun onItemShared(data: MovieEntity)
}
