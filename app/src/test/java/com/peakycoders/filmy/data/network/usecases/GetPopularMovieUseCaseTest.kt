package com.peakycoders.filmy.usecases

import com.peakycoders.filmy.entities.models.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetPopularMovieUseCaseTest{
    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun GetPopularMoviesIsNotEmpty() = runTest{
        //uspend operator fun invoke(page : Long) : List<Movie> = repository.getPopular(page)
        val pag = 1.toLong()
        val movie: List<Movie> = GetPopularMovieUseCase().invoke(page= pag)
        assertNotEquals(0, movie.size)
    }
    @Test
    fun GetPopularMoviesSearchingANullPage() = runTest{
        //uspend operator fun invoke(page : Long) : List<Movie> = repository.getPopular(page)
        val pag = 0.toLong()
        val movie: List<Movie> = GetPopularMovieUseCase().invoke(page= pag)
        assertEquals(0, movie.size)
    }

}