package com.peakycoders.filmy.data.database

import com.peakycoders.filmy.entities.models.Movie
import com.peakycoders.filmy.entities.patterns.Subject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieDataBase @Inject constructor(){
    var listPopular = emptyList<Movie>()
    var listNowPlaying = emptyList<Movie>()
}

//Se puede convertir en una DB de Room por ejemplo y darle persistencia al cerrar la app
object VisitedDB : Subject(){
    var listVisited = mutableListOf<Movie>()
    fun add (movie : Movie) {
        listVisited.remove(movie)
        listVisited.add(movie)
        notify(listVisited.toList().reversed())
    }
}