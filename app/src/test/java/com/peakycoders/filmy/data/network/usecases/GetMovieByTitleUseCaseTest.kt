package com.peakycoders.filmy.usecases

import com.peakycoders.filmy.entities.models.Movie
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetMovieByTitleUseCaseTest {

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun tearDown() {Dispatchers.resetMain()
    }

    @Test
    fun GetCorrectMovieByTitle() = runTest{
        val title = "Escuadrón suicida"
        val movie: List<Movie> = GetMovieByTitleUseCase().invoke(title)
        Assert.assertEquals(movie?.first().title, title)
    }
    @Test
    fun GetAIncorrectMovieByTitle() = runTest{
        val title = "Escuadrón suicida"
        val movie: List<Movie> = GetMovieByTitleUseCase().invoke(title)
        Assert.assertNotEquals(movie?.first().title, "")
    }
}