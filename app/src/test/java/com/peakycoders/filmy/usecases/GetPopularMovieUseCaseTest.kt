package com.peakycoders.filmy.usecases

import com.peakycoders.filmy.data.repository.MovieRepository
import com.peakycoders.filmy.entities.models.Movie
import com.peakycoders.filmy.mocks.MockMovies
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetPopularMovieUseCaseTest{
    @RelaxedMockK
    private lateinit var movieRepository: MovieRepository

    lateinit var getPopularMovieUseCase: GetPopularMovieUseCase
    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getPopularMovieUseCase = GetPopularMovieUseCase(movieRepository)
    }

    @Test
    fun `get popular movies is not empty`() = runTest{
        //Given
        coEvery { movieRepository.getPopular(1) } returns MockMovies.listMovie
        //When
        val result = getPopularMovieUseCase(1)
        //Then
        coVerify(exactly = 1) { movieRepository.getPopular(1) }
        assertEquals(result, MockMovies.listMovie)
    }
    @Test
    fun `get popular movies searching a null page`() = runTest{
        //Given
        coEvery { movieRepository.getPopular(1) } returns emptyList()
        //When
        val result = getPopularMovieUseCase(1)
        //Then
        coVerify(exactly = 1) { movieRepository.getPopular(1) }
        assertEquals(result, emptyList<Movie>())
    }

}