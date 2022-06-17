package com.peakycoders.filmy.ui.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.peakycoders.filmy.entities.TransferMovie
import com.peakycoders.filmy.mocks.MockCast
import com.peakycoders.filmy.mocks.MockMovies
import com.peakycoders.filmy.ui.patterns.*
import com.peakycoders.filmy.usecases.GetCastingUseCase
import com.peakycoders.filmy.usecases.GetVisitedMovieUseCase
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
class DetailsViewModelTest{
    @RelaxedMockK
    private lateinit var getCastingUseCase : GetCastingUseCase
    @RelaxedMockK
    private lateinit var getVisitedMovieUseCase : GetVisitedMovieUseCase

    private lateinit var detailsViewModel: DetailsViewModel

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
    fun `when viewmodel is created at the first time, get movie details and check title value`() = runTest{
        //Given
        TransferMovie.movie = MockMovies.movie0
        //When
        detailsViewModel = DetailsViewModel(getCastingUseCase, getVisitedMovieUseCase)
        //Then
        assert(detailsViewModel.movie?.title == MockMovies.movie0.title)
    }

    @Test
    fun `when viewmodel is created at the first time, check the visited movies`() = runTest{
        //Given
        TransferMovie.movie = MockMovies.movie0
        coEvery { getVisitedMovieUseCase() } returns listOf(MockMovies.movie0)
        //When
        detailsViewModel = DetailsViewModel(getCastingUseCase, getVisitedMovieUseCase)
        //Then
        assert(getVisitedMovieUseCase().contains(MockMovies.movie0))
    }

    @Test
    fun `check Success Response`(){
        //Given
        TransferMovie.movie = MockMovies.movie0
        coEvery { getCastingUseCase(1) } returns MockCast.listCast
        //When
        detailsViewModel = DetailsViewModel(getCastingUseCase, getVisitedMovieUseCase)
        //Then
        assert(
            detailsViewModel.response.value.toString()  == Response(
                Success( SuccessCast(MockCast.listCast) )
            ).toString()
        )
    }

    @Test
    fun `check Loading Response`(){
        //Given
        detailsViewModel = DetailsViewModel(getCastingUseCase, getVisitedMovieUseCase)
        //When
        detailsViewModel.response.value = Response(Loading(LoadingHorizontal()))
        //Then
        assert(
            detailsViewModel.response.value.toString() == Response(Loading(LoadingHorizontal())).toString()
        )
    }

    @Test
    fun `check Error Response`(){
        //Given
        TransferMovie.movie = MockMovies.movie0
        coEvery { getCastingUseCase(1) } returns emptyList()
        //When
        detailsViewModel = DetailsViewModel(getCastingUseCase, getVisitedMovieUseCase)
        //Then
        assert(
            detailsViewModel.response.value.toString()  == Response(
                Error("Se produjo un error al obtener los actores")
            ).toString()
        )
    }
}