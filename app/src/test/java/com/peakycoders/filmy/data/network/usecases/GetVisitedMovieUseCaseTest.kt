package com.peakycoders.filmy.data.network.usecases

import com.peakycoders.filmy.entities.models.Movie
import com.peakycoders.filmy.usecases.GetMovieByIDUseCase
import com.peakycoders.filmy.usecases.GetVisitedMovieUseCase
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
    fun trySaveAdataInARepositoryIfItIsPossibleTestPass()= runTest {
        val id = 297761.toLong()
        val movie: Movie? = GetMovieByIDUseCase().invoke(id)
        if(movie!=null){
            GetVisitedMovieUseCase().save(movie)
        }
        GetVisitedMovieUseCase().invoke()
        assert(GetVisitedMovieUseCase().invoke().size>0)
    }

    @Test
    fun trySaveAdataInARepositoryIfItIsPossibleTestFail()= runTest {
        val id = 0.toLong()
        val movie: Movie? = GetMovieByIDUseCase().invoke(id)
        if(movie!=null){
            GetVisitedMovieUseCase().save(movie)
        }
        GetVisitedMovieUseCase().invoke()
        assert(GetVisitedMovieUseCase().invoke().size==0)
    }
}