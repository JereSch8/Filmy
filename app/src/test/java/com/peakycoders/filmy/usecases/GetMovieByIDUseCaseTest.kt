package com.peakycoders.filmy.usecases

import com.peakycoders.filmy.entities.models.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
@ExperimentalCoroutinesApi
class GetMovieByIDUseCaseTest {

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun CorrectSearchById()= runTest {
        val id = 297761.toLong()

        val movie: Movie? = GetMovieByIDUseCase().invoke(id)

        assertEquals(true, movie != null)

        if (movie != null) {
            assertEquals(id, movie.id)
        }
    }
    @Test
    fun `invalid id search`()= runTest {
        val id = -1.toLong()

        val movie: Movie? = GetMovieByIDUseCase().invoke(id)

        assertEquals(true, movie == null)
    }
    @Test
    fun `film search name is espected`()= runTest {
        val id = 297761.toLong()
        val stringExpected = "Escuadrón suicida"
        val movie: Movie? = GetMovieByIDUseCase().invoke(id)
        assertEquals(movie?.title, stringExpected)
    }

}