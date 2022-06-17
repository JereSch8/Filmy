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

import org.junit.Before
import org.junit.Test
@ExperimentalCoroutinesApi
class GetMovieByIDUseCaseTest {
    @RelaxedMockK
    private lateinit var movieRepository: MovieRepository

    lateinit var getMovieByIDUseCase: GetMovieByIDUseCase
    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getMovieByIDUseCase = GetMovieByIDUseCase(movieRepository)
    }

    @Test
    fun `correct search by id`()= runTest {
        //Given
        coEvery { movieRepository.getByID(1) } returns MockMovies.movie0
        //When
        val result = getMovieByIDUseCase(1)
        //Then
        coVerify(exactly = 1) { movieRepository.getByID(1) }
        assert(1.toLong() == result?.id )
    }

    @Test
    fun `invalid id search`()= runTest {
        //Given
        coEvery { movieRepository.getByID(-1) } returns null
        //When
        val result = getMovieByIDUseCase(-1)
        //Then
        coVerify(exactly = 1) { movieRepository.getByID(-1) }
        assertNull(result)
    }

    @Test
    fun `film search name is espected`()= runTest {
        //Given
        coEvery { movieRepository.getByID(457) } returns MockMovies.movie2
        //When
        val result = getMovieByIDUseCase(457)
        //Then
        coVerify(exactly = 1) { movieRepository.getByID(457) }
        assertEquals(MockMovies.movie2.title, result?.title)
    }

}