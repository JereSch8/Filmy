package com.peakycoders.filmy.ui.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peakycoders.filmy.data.database.VisitedDB
import com.peakycoders.filmy.entities.models.Movie
import com.peakycoders.filmy.entities.patterns.Observer
import com.peakycoders.filmy.usecases.GetNowPlayingMovieUseCase
import com.peakycoders.filmy.usecases.GetPopularMovieUseCase
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel(), Observer {
    val popularMovies : MutableState<List<Movie>> = mutableStateOf(listOf())
    val nowPlayingMovies : MutableState<List<Movie>> = mutableStateOf(listOf())
    val visitedMovies : MutableState<List<Movie>> = mutableStateOf(listOf())
    val isLoading : MutableState<Boolean> = mutableStateOf(false)

    private var getPopularMovieUseCase = GetPopularMovieUseCase()
    private var getPlayinNowMovieUseCase = GetNowPlayingMovieUseCase()

     init {
         VisitedDB.attach(this)
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

            isLoading.value = false
        }
    }

    override fun update(notice: List<Movie>) {
        visitedMovies.value = notice
    }
}