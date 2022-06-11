package com.peakycoders.filmy.ui.details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peakycoders.filmy.entities.TransferMovie
import com.peakycoders.filmy.entities.models.Movie
import com.peakycoders.filmy.ui.patterns.*
import com.peakycoders.filmy.usecases.GetCastingUseCase
import com.peakycoders.filmy.usecases.GetVisitedMovieUseCase
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel() {
    val movie : Movie? = TransferMovie.movie
    val response : MutableState<Response> = mutableStateOf(Response(Loading()))
    private var getCastingUseCase = GetCastingUseCase()

    init {
        response.value = Response(Loading())

        if (movie != null){
            GetVisitedMovieUseCase().save(movie)
            viewModelScope.launch {
                val casting = getCastingUseCase(movie.id)
                if(casting.isNotEmpty())
                    response.value = Response(
                        Success(
                            SuccessCast(casting)
                        )
                    )
                else
                    response.value = Response(
                        Error("Se produjo un error al obtener los actores")
                    )
            }
        }
    }
}