package com.peakycoders.filmy.usecases

import com.peakycoders.filmy.data.repository.MovieRepository
import com.peakycoders.filmy.entities.models.Movie
import javax.inject.Inject

class GetPopularMovieUseCase @Inject constructor(
    private val repository : MovieRepository
){
    suspend operator fun invoke(page : Long) : List<Movie> = repository.getPopular(page)
}