package com.syahrizal.submission2daftarfilm.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.syahrizal.submission2daftarfilm.data.MovieCatalogueRepository
import com.syahrizal.submission2daftarfilm.data.source.local.entity.TVShowEntity
import com.syahrizal.submission2daftarfilm.utils.DataDummy.generateDummyTVShows
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TVShowsViewModelTest {

    private lateinit var viewModel: TVShowsViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieCatalogueRepository: MovieCatalogueRepository

    @Mock
    private lateinit var observer: Observer<List<TVShowEntity>>

    @Before
    fun setUp() {
        viewModel = TVShowsViewModel(movieCatalogueRepository)
    }

    @Test
    fun getTvShows() {
        val dummyTvShows = generateDummyTVShows()
        val tvShows = MutableLiveData<List<TVShowEntity>>()
        tvShows.value = dummyTvShows
        `when`(movieCatalogueRepository.getTvShows()).thenReturn(tvShows)
        val tvShowEntities = viewModel.getTvShows().value
        verify(movieCatalogueRepository).getTvShows()
        assertNotNull(tvShowEntities)
        assertEquals(3, tvShowEntities?.size)
        viewModel.getTvShows().observeForever(observer)
        verify(observer).onChanged(dummyTvShows)
    }
}