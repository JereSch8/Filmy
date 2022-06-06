package com.peakycoders.filmy.usecases

import com.peakycoders.filmy.entities.models.Movie

class GetPopularMovieUseCase : BaseMovieUseCase() {
    suspend operator fun invoke(page : Long) : List<Movie> = repository.getPopular(page)
}