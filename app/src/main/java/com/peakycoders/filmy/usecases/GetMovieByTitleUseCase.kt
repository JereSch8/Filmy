package com.peakycoders.filmy.usecases

import com.peakycoders.filmy.entities.models.Movie

class GetMovieByTitleUseCase : BaseMovieUseCase(){
    suspend operator fun invoke(title : String) : List<Movie> = repository.getByTitle(title)
}