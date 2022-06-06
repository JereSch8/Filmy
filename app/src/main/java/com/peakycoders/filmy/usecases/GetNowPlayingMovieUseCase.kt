package com.peakycoders.filmy.usecases

import com.peakycoders.filmy.entities.models.Movie

class GetNowPlayingMovieUseCase : BaseMovieUseCase(){
    suspend operator fun invoke() : List<Movie> =  repository.getNowPlaying()

}