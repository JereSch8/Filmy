package com.peakycoders.filmy.usecases

import com.peakycoders.filmy.entities.models.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
internal class GetVisitedMovieUseCaseTest{
    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
    @Test
    fun `try save a data in a Repository if it is possible test pass`()= runTest {
        val id = 297761.toLong()
        val movie: Movie? = GetMovieByIDUseCase().invoke(id)
        if(movie!=null){
            GetVisitedMovieUseCase().save(movie)
        }
        GetVisitedMovieUseCase().invoke()
        assert(GetVisitedMovieUseCase().invoke().isNotEmpty())
    }

    @Test
    fun `try save a data in a Repository if it is possible test fail`()= runTest {
        val id = 0.toLong()
        val movie: Movie? = GetMovieByIDUseCase().invoke(id)
        if(movie!=null){
            GetVisitedMovieUseCase().save(movie)
        }
        GetVisitedMovieUseCase().invoke()
        assert(GetVisitedMovieUseCase().invoke().isEmpty())
    }
}