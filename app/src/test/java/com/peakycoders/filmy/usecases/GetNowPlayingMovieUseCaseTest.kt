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
import org.junit.Assert

import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetNowPlayingMovieUseCaseTest(){
    @RelaxedMockK
    private lateinit var movieRepository: MovieRepository

    lateinit var getNowPlayingMovieUseCase: GetNowPlayingMovieUseCase
    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getNowPlayingMovieUseCase = GetNowPlayingMovieUseCase(movieRepository)
    }

    @Test
    fun `get now playing movies is not empty`() = runTest{
        //Given
        coEvery { movieRepository.getNowPlaying() } returns MockMovies.listMovie
        //When
        val result = getNowPlayingMovieUseCase()
        //Then
        coVerify(exactly = 1) { movieRepository.getNowPlaying() }
        Assert.assertEquals(result, MockMovies.listMovie)
    }

    @Test
    fun `get now playing movies is empty`() = runTest{
        //Given
        coEvery { movieRepository.getNowPlaying() } returns emptyList()
        //When
        val result = getNowPlayingMovieUseCase()
        //Then
        coVerify(exactly = 1) { movieRepository.getNowPlaying() }
        Assert.assertEquals(result, emptyList<Movie>())
    }
}