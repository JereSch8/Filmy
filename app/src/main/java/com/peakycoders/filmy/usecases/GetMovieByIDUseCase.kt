package com.peakycoders.filmy.usecases

import com.peakycoders.filmy.entities.models.Movie

class GetMovieByIDUseCase : BaseMovieUseCase(){
    suspend operator fun invoke(id : Long) : Movie? = repository.getByID(id)
}