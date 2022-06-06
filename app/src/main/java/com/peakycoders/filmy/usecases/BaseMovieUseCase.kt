package com.peakycoders.filmy.usecases

import com.peakycoders.filmy.data.repository.MovieRepository

open class BaseMovieUseCase {
    protected val repository = MovieRepository()
}