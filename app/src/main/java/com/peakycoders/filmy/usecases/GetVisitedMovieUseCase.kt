package com.peakycoders.filmy.usecases

import com.peakycoders.filmy.entities.models.Movie

class GetVisitedMovieUseCase : BaseMovieUseCase(){
    operator fun invoke() : List<Movie> =  repository.getVisited()
    fun save(movie : Movie) = repository.setVisited(movie)
}