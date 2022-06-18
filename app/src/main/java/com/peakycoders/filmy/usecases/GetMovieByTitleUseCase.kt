package com.peakycoders.filmy.usecases

import com.peakycoders.filmy.data.repository.MovieRepository
import com.peakycoders.filmy.entities.models.Movie
import javax.inject.Inject

class GetMovieByTitleUseCase @Inject constructor(
    private val repository : MovieRepository
){
    suspend operator fun invoke(title : String) : List<Movie> = repository.getByTitle(title)
}