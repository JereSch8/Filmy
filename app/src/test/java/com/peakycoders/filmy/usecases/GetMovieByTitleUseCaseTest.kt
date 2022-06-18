package com.peakycoders.filmy.usecases

import com.peakycoders.filmy.data.repository.MovieRepository
import com.peakycoders.filmy.mocks.MockMovies
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
@ExperimentalCoroutinesApi
class GetMovieByTitleUseCaseTest {
    @RelaxedMockK
    private lateinit var movieRepository: MovieRepository

    lateinit var getMovieByTitleUseCase: GetMovieByTitleUseCase
    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getMovieByTitleUseCase = GetMovieByTitleUseCase(movieRepository)
    }

    @Test
    fun `get correct movie by title`() = runTest{
        //Given
        coEvery { movieRepository.getByTitle(MockMovies.movie0.title) } returns MockMovies.listMovie
        //When
        val result = getMovieByTitleUseCase(MockMovies.movie0.title)
        //Then
        coVerify(exactly = 1) { movieRepository.getByTitle(MockMovies.movie0.title) }
        result.forEach { movie ->
            assertEquals(movie.title, MockMovies.movie0.title )
        }
    }

    @Test
    fun `get aIncorrect movie by title`() = runTest{
        //Given
        coEvery { movieRepository.getByTitle(MockMovies.movie0.title) } returns emptyList()
        //When
        val result = getMovieByTitleUseCase(MockMovies.movie0.title)
        //Then
        coVerify(exactly = 1) { movieRepository.getByTitle(MockMovies.movie0.title) }
        assert(result.isEmpty())
    }
}