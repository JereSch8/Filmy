package com.peakycoders.filmy.usecases

import com.peakycoders.filmy.data.repository.MovieRepository
import com.peakycoders.filmy.mocks.MockMovies
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
internal class GetVisitedMovieUseCaseTest{
    @RelaxedMockK
    private lateinit var movieRepository: MovieRepository

    lateinit var getVisitedMovieUseCase: GetVisitedMovieUseCase
    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getVisitedMovieUseCase = GetVisitedMovieUseCase(movieRepository)
    }

    @Test
    fun `try save a data in a Repository if it is possible test pass`()= runTest {
        //Given
        coEvery { movieRepository.getVisited() } returns listOf(MockMovies.movie2)
        //When
        getVisitedMovieUseCase.save(MockMovies.movie2)
        //Then
        coVerify(exactly = 1) { movieRepository.setVisited(MockMovies.movie2) }
        assert(getVisitedMovieUseCase().contains(MockMovies.movie2))
    }

    @Test
    fun `get data of a Repository if it is possible`()= runTest {
        //Given
        coEvery { movieRepository.getVisited() } returns listOf(MockMovies.movie2)
        //When
        movieRepository.setVisited(MockMovies.movie2)
        val result = getVisitedMovieUseCase()
        //Then
        coVerify(exactly = 1) { movieRepository.setVisited(MockMovies.movie2) }
        coVerify(exactly = 1) { movieRepository.getVisited() }
        assert(result.contains(MockMovies.movie2))
    }
}