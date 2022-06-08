package com.peakycoders.filmy.data.repository

import com.peakycoders.filmy.data.database.MovieDataBase
import com.peakycoders.filmy.data.database.VisitedDB
import com.peakycoders.filmy.data.network.MovieService
import com.peakycoders.filmy.entities.models.Movie

/*
    Patron Repository, es quien se encarga de decidir si las peliculas
    serán buscado en la red o si se leeran de la DB local
 */
class MovieRepository {
    private val api = MovieService()

    suspend fun getByID(id : Long) : Movie? = api.getByID( id )

    suspend fun getByTitle(title : String) : List<Movie> = api.getByTitle( title )

    suspend fun getNowPlaying() : List<Movie>{
        if(MovieDataBase.listNowPlaying.isEmpty()){
            val response = api.getNowPlaying()
            MovieDataBase.listNowPlaying = response
        }
        return  MovieDataBase.listNowPlaying
    }

    suspend fun getPopular(page : Long) : List<Movie>{
        if (MovieDataBase.listPopular.isEmpty()) {
            val response = api.getPopular(page)
            MovieDataBase.listPopular = response
        }
        return MovieDataBase.listPopular
    }

    fun getVisited() : List<Movie> = VisitedDB.listVisited
    fun setVisited(movie : Movie) = VisitedDB.add(movie)
}