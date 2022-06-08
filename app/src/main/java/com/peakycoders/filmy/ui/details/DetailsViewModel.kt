package com.peakycoders.filmy.ui.details

import androidx.lifecycle.ViewModel
import com.peakycoders.filmy.entities.TransferMovie
import com.peakycoders.filmy.entities.models.Movie
import com.peakycoders.filmy.usecases.GetVisitedMovieUseCase

class DetailsViewModel : ViewModel() {
    //Constante de nombre movie de tipo Movie que puede ser nula y se obtiene de
    //
    val movie : Movie? = TransferMovie.movie

    init {
        if (movie != null) GetVisitedMovieUseCase().save(movie)
    }
}