package com.peakycoders.filmy.ui.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peakycoders.filmy.entities.models.Movie
import com.peakycoders.filmy.usecases.GetNowPlayingMovieUseCase
import com.peakycoders.filmy.usecases.GetPopularMovieUseCase
import com.peakycoders.filmy.usecases.GetVisitedMovieUseCase
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    val popularMovies : MutableState<List<Movie>> = mutableStateOf(listOf())
    val nowPlayingMovies : MutableState<List<Movie>> = mutableStateOf(listOf())
    val visitedMovies : MutableState<List<Movie>> = mutableStateOf(listOf())
    val isLoading : MutableState<Boolean> = mutableStateOf(false)

    private var getPopularMovieUseCase = GetPopularMovieUseCase()
    private var getPlayinNowMovieUseCase = GetNowPlayingMovieUseCase()
    private var getVisitedMovieUseCase = GetVisitedMovieUseCase()

     init {
        viewModelScope.launch {
            isLoading.value = true

            val moviesNowPlaying = getPlayinNowMovieUseCase()
            if (moviesNowPlaying.isNotEmpty()){
                nowPlayingMovies.value = moviesNowPlaying
            }
            val moviesPopular = getPopularMovieUseCase(1)
            if (moviesPopular.isNotEmpty()){
                popularMovies.value = moviesPopular
            }

            fakeUpdate() //Esta acá de prueba nada mas
            isLoading.value = false
        }
    }

    //TODO: Agregar feature del observer para actualizar las peliculas visitadas
    private fun fakeUpdate(){
        if (popularMovies.value.isNotEmpty()){
            getVisitedMovieUseCase.save(popularMovies.value[0])
            getVisitedMovieUseCase.save(popularMovies.value[3])
            getVisitedMovieUseCase.save(popularMovies.value[1])
            visitedMovies.value = getVisitedMovieUseCase()
        }
    }
}