package com.peakycoders.filmy.usecases

import com.peakycoders.filmy.entities.models.Movie

class GetSearchMovieUseCase : BaseMovieUseCase() {
    suspend operator fun invoke(query : String) : List<Movie> = repository.searchMovie(query)
}