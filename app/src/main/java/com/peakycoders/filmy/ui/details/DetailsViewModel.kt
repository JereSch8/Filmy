package com.peakycoders.filmy.ui.details

import androidx.lifecycle.ViewModel
import com.peakycoders.filmy.entities.TransferMovie
import com.peakycoders.filmy.entities.models.Movie
import com.peakycoders.filmy.usecases.GetVisitedMovieUseCase

class DetailsViewModel : ViewModel() {
    val movie : Movie? = TransferMovie.movie

    init {
        if (movie != null) GetVisitedMovieUseCase().save(movie)
    }
}