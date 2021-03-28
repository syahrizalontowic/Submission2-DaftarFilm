package com.syahrizal.submission2daftarfilm.ui.callback

import com.syahrizal.submission2daftarfilm.data.source.local.entity.TVShowEntity


interface OnTVShowItemClickCallback {
    fun onItemClicked(data: TVShowEntity)
    fun onItemShared(data: TVShowEntity)
}
