package com.peakycoders.filmy.ui.details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peakycoders.filmy.entities.TransferMovie
import com.peakycoders.filmy.entities.models.Cast
import com.peakycoders.filmy.entities.models.Movie
import com.peakycoders.filmy.usecases.GetCastingUseCase
import com.peakycoders.filmy.usecases.GetVisitedMovieUseCase
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel() {
    val movie : Movie? = TransferMovie.movie
    val casting : MutableState<List<Cast>> = mutableStateOf(listOf())
    private var getCastingUseCase = GetCastingUseCase()

    init {
        if (movie != null){
            GetVisitedMovieUseCase().save(movie)
            viewModelScope.launch {
                casting.value = getCastingUseCase(movie.id)
            }
        }
    }
}