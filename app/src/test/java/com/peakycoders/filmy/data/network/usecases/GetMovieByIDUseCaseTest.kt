package com.peakycoders.filmy.usecases

import com.peakycoders.filmy.entities.models.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetMovieByIDUseCaseTest {
    //@RelaxedMockK
    //private lateinit var retrofit: Retrofit
    //private val retrofit = Helper.getRetrofit()
    //private lateinit var movieService: MovieService

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

        Assert.assertEquals(true, movie != null)

        if (movie != null) {
            Assert.assertEquals(id, movie.id)
        }
    }
    @Test
    fun InvalidIdSearch()= runTest {
        val id = -1.toLong()

        val movie: Movie? = GetMovieByIDUseCase().invoke(id)

        Assert.assertEquals(true, movie == null)
    }
    @Test
    fun FilmSearchNameIsEspected()= runTest {
        val id = 297761.toLong()
        val stringExpected = "Escuadrón suicida"
        val movie: Movie? = GetMovieByIDUseCase().invoke(id)
        Assert.assertEquals(movie?.title, stringExpected)
    }

}