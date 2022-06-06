package com.peakycoders.filmy.data.database

import com.peakycoders.filmy.entities.models.Movie

class MovieDataBase {
    companion object{
        var listPopular = emptyList<Movie>()
        var listNowPlaying = emptyList<Movie>()
        var listVisited = mutableSetOf<Movie>()
    }
}