package com.peakycoders.filmy.usecases

import com.peakycoders.filmy.data.repository.MovieRepository
import com.peakycoders.filmy.entities.models.Movie
import javax.inject.Inject

class GetSearchMovieUseCase @Inject constructor(
    private val repository : MovieRepository
){
    suspend operator fun invoke(query : String) : List<Movie> = repository.searchMovie(query)
}