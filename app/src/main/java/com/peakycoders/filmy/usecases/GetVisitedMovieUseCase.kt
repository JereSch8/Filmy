package com.peakycoders.filmy.usecases

import com.peakycoders.filmy.data.repository.MovieRepository
import com.peakycoders.filmy.entities.models.Movie
import javax.inject.Inject

class GetVisitedMovieUseCase @Inject constructor(
    private val repository : MovieRepository
){
    operator fun invoke() : List<Movie> =  repository.getVisited()
    fun save(movie : Movie) = repository.setVisited(movie)
}