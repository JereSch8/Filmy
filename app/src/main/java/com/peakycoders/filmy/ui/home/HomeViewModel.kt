package com.peakycoders.filmy.ui.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peakycoders.filmy.entities.models.Movie
import com.peakycoders.filmy.usecases.GetNowPlayingMovieUseCase
import com.peakycoders.filmy.usecases.GetPopularMovieUseCase
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    val popularMovies : MutableState<List<Movie>> = mutableStateOf(listOf())
    val isLoading : MutableState<Boolean> = mutableStateOf(false)

    var getPopularMovieUseCase = GetPopularMovieUseCase()
    var getPlayinNowMovieUseCase = GetNowPlayingMovieUseCase()

     init {
        viewModelScope.launch {
            isLoading.value = true
            val response = getPlayinNowMovieUseCase()
            if (response.isNotEmpty()){
                popularMovies.value = response
            }
            isLoading.value = false
        }
    }
}