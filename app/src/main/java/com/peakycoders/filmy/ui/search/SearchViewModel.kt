package com.peakycoders.filmy.ui.search

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peakycoders.filmy.ui.patterns.*
import com.peakycoders.filmy.usecases.GetSearchMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchMovieUseCase : GetSearchMovieUseCase
): ViewModel() {
    val resultSearch : MutableState<Response> = mutableStateOf(Response(Error("")))

    fun search(query : String){
        viewModelScope.launch {
            resultSearch.value = Response(Loading())
            val listMovie = searchMovieUseCase(query)
            if (listMovie.isNotEmpty())
                resultSearch.value = Response(
                    Success(
                        SuccessSearchMovie(listMovie)
                    )
                )
            else
                resultSearch.value = Response(
                    Error("No se encontró la pelicula buscada")
                )
        }
    }

    private val _searchTextState: MutableState<String> = mutableStateOf(value = "")
    val searchTextState: State<String> = _searchTextState

    fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
    }
}