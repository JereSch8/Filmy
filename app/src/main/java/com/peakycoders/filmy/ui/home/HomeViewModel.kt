package com.peakycoders.filmy.ui.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peakycoders.filmy.data.database.VisitedDB
import com.peakycoders.filmy.entities.models.Movie
import com.peakycoders.filmy.entities.patterns.Observer
import com.peakycoders.filmy.ui.patterns.*
import com.peakycoders.filmy.usecases.GetNowPlayingMovieUseCase
import com.peakycoders.filmy.usecases.GetPopularMovieUseCase
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel(), Observer {
    val visitedMovies : MutableState<Response> = mutableStateOf(Response(Error("")))

    val responsePopular : MutableState<Response> = mutableStateOf(Response(Loading()))
    val responseNowPlaying : MutableState<Response> = mutableStateOf(Response(Loading()))

    private var getPopularMovieUseCase = GetPopularMovieUseCase()
    private var getPlayinNowMovieUseCase = GetNowPlayingMovieUseCase()

     init {
         VisitedDB.attach(this)
         viewModelScope.launch {
            val moviesNowPlaying = getPlayinNowMovieUseCase()
            if (moviesNowPlaying.isNotEmpty())
                responseNowPlaying.value = Response(
                    Success(
                        SuccessMovie(moviesNowPlaying)
                    )
                )
            else
                responseNowPlaying.value = Response(
                    Error("Se produjo un error al obtener las peliculas del momento")
                )

            val moviesPopular = getPopularMovieUseCase(1)
            if (moviesPopular.isNotEmpty())//{
                responsePopular.value = Response(
                    Success(
                        SuccessMovie(moviesPopular)
                    )
                )
            else
                responsePopular.value = Response(
                    Error("Se produjo un error al obtener las peliculas populares")
                )
        }
    }

    override fun update(notice: List<Movie>) {
        if (notice.isNotEmpty()){
            visitedMovies.value = Response(
                Success(
                    SuccessMovie(notice)
                )
            )
        }
    }
}