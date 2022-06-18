package com.peakycoders.filmy.ui.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.peakycoders.filmy.mocks.MockMovies
import com.peakycoders.filmy.ui.patterns.*
import com.peakycoders.filmy.usecases.GetSearchMovieUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule

import org.junit.Test

@ExperimentalCoroutinesApi
class SearchViewModelTest {
    @RelaxedMockK
    private lateinit var searchMovieUseCase : GetSearchMovieUseCase

    private lateinit var searchViewModel: SearchViewModel

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
    fun `get search text state`() {
        //Given
        searchViewModel = SearchViewModel(searchMovieUseCase)
        //When
        //Then
        assert( searchViewModel.searchTextState.value == "" )
    }

    @Test
    fun `update search text state`() {
        //Given
        searchViewModel = SearchViewModel(searchMovieUseCase)
        //When
        searchViewModel.updateSearchTextState("TestValue")
        //Then
        assert( searchViewModel.searchTextState.value == "TestValue" )
    }

    @Test
    fun `check Success Response`(){
        //Given
        coEvery { searchMovieUseCase("LosMocks") } returns MockMovies.listMovie
        searchViewModel = SearchViewModel(searchMovieUseCase)
        //When
        searchViewModel.search("LosMocks")
        //Then
        assert(
            searchViewModel.resultSearch.value.toString()  == Response(
                Success(SuccessSearchMovie( MockMovies.listMovie) )
            ).toString()
        )
    }

    @Test
    fun `check Loading Response`(){
        //Given
        searchViewModel = SearchViewModel(searchMovieUseCase)
        //When
        searchViewModel.resultSearch.value = Response( Loading(LoadingVertical()) )
        //Then
        assert(
            searchViewModel.resultSearch.value.toString() == Response(Loading(LoadingVertical())).toString()
        )
    }

    @Test
    fun `check Error Response`(){
        //Given
        coEvery { searchMovieUseCase("LosMocks") } returns emptyList()
        searchViewModel = SearchViewModel(searchMovieUseCase)
        //When
        searchViewModel.search("LosMocks")
        //Then
        assert(
            searchViewModel.resultSearch.value.toString()  == Response(
                Error("No se encontró la pelicula buscada")
            ).toString()
        )
    }

    @Test
    fun `check null input`(){
        //Given
        coEvery { searchMovieUseCase("") } returns emptyList()
        searchViewModel = SearchViewModel(searchMovieUseCase)
        //When
        searchViewModel.search("")
        //Then
        assert(
            searchViewModel.resultSearch.value.toString()  == Response(
                Error("No se encontró la pelicula buscada")
            ).toString()
        )
    }
}