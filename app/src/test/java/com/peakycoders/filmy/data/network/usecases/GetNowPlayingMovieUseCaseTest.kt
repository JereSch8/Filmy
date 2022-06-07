package com.peakycoders.filmy.usecases

import com.peakycoders.filmy.entities.models.Movie

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
@ExperimentalCoroutinesApi
class GetNowPlayingMovieUseCaseTest(){
    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun GetNowPlayingMoviesIsNotEmpty() = runTest{
        val movie: List<Movie> = GetNowPlayingMovieUseCase().invoke()
        assertNotEquals(0, movie.size)
    }
}