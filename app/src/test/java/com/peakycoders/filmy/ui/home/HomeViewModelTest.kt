package com.peakycoders.filmy.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.peakycoders.filmy.data.database.VisitedDB
import com.peakycoders.filmy.mocks.MockMovies
import com.peakycoders.filmy.ui.patterns.*
import com.peakycoders.filmy.usecases.GetNowPlayingMovieUseCase
import com.peakycoders.filmy.usecases.GetPopularMovieUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {
    @RelaxedMockK
    private lateinit var getPopularMovieUseCase : GetPopularMovieUseCase
    @RelaxedMockK
    private lateinit var getPlayinNowMovieUseCase : GetNowPlayingMovieUseCase

    private lateinit var homeViewModel: HomeViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() = Dispatchers.resetMain()

    @Test
    fun `get visited movies`() = runTest{
        //Given
        homeViewModel = HomeViewModel(getPopularMovieUseCase, getPlayinNowMovieUseCase)
        //When
        homeViewModel.visitedMovies.value = Response(
            Success(SuccessMovie(MockMovies.listMovie))
        )
        //Then
        assert(
            homeViewModel.visitedMovies.value.toString() == Response(
                Success(SuccessMovie(MockMovies.listMovie))
            ).toString()
        )
    }

    @Test
    fun `get response popular Success`() = runTest{
        //Given
        coEvery { getPopularMovieUseCase(1) } returns MockMovies.listMovie
        //When
        homeViewModel = HomeViewModel(getPopularMovieUseCase, getPlayinNowMovieUseCase)
        //Then
        assert(
            homeViewModel.responsePopular.value.toString()  == Response(
                Success( SuccessMovie( MockMovies.listMovie ) )
            ).toString()
        )
    }

    @Test
    fun `get response popular Error`() = runTest{
        //Given
        coEvery { getPopularMovieUseCase(1) } returns emptyList()
        //When
        homeViewModel = HomeViewModel(getPopularMovieUseCase, getPlayinNowMovieUseCase)
        //Then
        assert(
            homeViewModel.responsePopular.value.toString() == Response(
                Error("Se produjo un error al obtener las peliculas populares")
            ).toString()
        )
    }

    @Test
    fun `get response popular Loading`() = runTest{
        //Given
        homeViewModel = HomeViewModel(getPopularMovieUseCase, getPlayinNowMovieUseCase)
        //When
        homeViewModel.responsePopular.value = Response(Loading(LoadingVertical()))
        //Then
        assert(
            homeViewModel.responsePopular.value.toString() == Response(Loading(LoadingVertical())).toString()
        )
    }

    @Test
    fun `get response now playing Success`() = runTest{
        //Given
        coEvery { getPlayinNowMovieUseCase() } returns MockMovies.listMovie
        //When
        homeViewModel = HomeViewModel(getPopularMovieUseCase, getPlayinNowMovieUseCase)
        //Then
        assert(
            homeViewModel.responseNowPlaying.value.toString()  == Response(
                Success( SuccessMovie( MockMovies.listMovie ) )
            ).toString()
        )
    }

    @Test
    fun `get response now playing Error`() = runTest{
        //Given
        coEvery { getPlayinNowMovieUseCase() } returns emptyList()
        //When
        homeViewModel = HomeViewModel(getPopularMovieUseCase, getPlayinNowMovieUseCase)
        //Then
        assert(
            homeViewModel.responseNowPlaying.value.toString() == Response(
                Error("Se produjo un error al obtener las peliculas del momento")
            ).toString()
        )
    }

    @Test
    fun `get response now playing Loading`() = runTest{
        //Given
        homeViewModel = HomeViewModel(getPopularMovieUseCase, getPlayinNowMovieUseCase)
        //When
        homeViewModel.responseNowPlaying.value = Response(Loading(LoadingHorizontal()))
        //Then
        assert(
            homeViewModel.responseNowPlaying.value.toString() == Response(Loading(LoadingHorizontal())).toString()
        )
    }

    @Test
    fun `get update from singleton`() = runTest{
        //Given
        homeViewModel = HomeViewModel(getPopularMovieUseCase, getPlayinNowMovieUseCase)
        //When
        homeViewModel.update(MockMovies.listMovie)
        //Then
        assert(
            homeViewModel.visitedMovies.value.toString() == Response(
                Success(SuccessMovie(MockMovies.listMovie))
            ).toString()
        )
    }

    @Test
    fun `check attach viewmodel to movieDB changes`(){
        //Given
        homeViewModel = HomeViewModel(getPopularMovieUseCase, getPlayinNowMovieUseCase)
        //When
        VisitedDB.notify(MockMovies.listMovie)
        //Then
        assert(
            homeViewModel.visitedMovies.value.toString() == Response(
                Success(SuccessMovie(MockMovies.listMovie))
            ).toString()
        )
    }
}