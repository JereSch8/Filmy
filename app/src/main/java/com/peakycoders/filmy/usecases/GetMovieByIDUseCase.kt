package com.peakycoders.filmy.usecases

import com.peakycoders.filmy.data.repository.MovieRepository
import com.peakycoders.filmy.entities.models.Movie
import javax.inject.Inject

class GetMovieByIDUseCase @Inject constructor(
    private val repository : MovieRepository
){
    suspend operator fun invoke(id : Long) : Movie? = repository.getByID(id)
}