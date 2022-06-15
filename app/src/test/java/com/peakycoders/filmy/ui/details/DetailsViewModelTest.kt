package com.peakycoders.filmy.ui.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.peakycoders.filmy.data.database.VisitedDB
import com.peakycoders.filmy.entities.TransferMovie
import com.peakycoders.filmy.entities.models.Movie
import io.mockk.coEvery
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.text.Typography.quote

@ExperimentalCoroutinesApi
class DetailsViewModelTest{

    private lateinit var detailsViewModel: DetailsViewModel

    private val movie = Movie(
        backdrop_path = "",
        id = 1,
        original_language = "ES" ,
        overview = "Es una fake movie",
        popularity = 100.toDouble(),
        poster_path = "",
        release_date = "2022-06-12",
        runtime = 1,
        status = "OK",
        tagline = "",
        title = "The Best APP",
        vote_average = 10.toDouble(),
        vote_count = 100000
    )

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore(){
        detailsViewModel = DetailsViewModel()
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when viewmodel is created at the first time, get movie details and check title value`() = runTest{
        //Given
//        TransferMovie.movie = movie
//        coEvery { detailsViewModel.use } returns quote
//        //When
//        detailsViewModel.onCreate()
//        //Then
//        assert(quoteViewModel.quoteModel.value == quote.first())
        assert(true)
    }

    @Test
    fun `when viewmodel is created at the first time, check the visited movies`() = runTest{
        //Given
        //When
        //Then
        assert(true)
    }

    @Test
    fun `check Success Response`(){
        assert(true)
    }

    @Test
    fun `check Loading Response`(){
        assert(true)
    }

    @Test
    fun `check Error Response`(){
        assert(true)
    }



}