package com.peakycoders.filmy.data.repository

import com.peakycoders.filmy.data.database.MovieDataBase
import com.peakycoders.filmy.data.database.VisitedDB
import com.peakycoders.filmy.data.network.MovieService
import com.peakycoders.filmy.entities.models.Movie
import javax.inject.Inject

/*
    Patron Repository, es quien se encarga de decidir si las peliculas
    serán buscado en la red o si se leeran de la DB local
 */
class MovieRepository @Inject constructor(
    private val api : MovieService,
    private val movieDataBase: MovieDataBase
) {
    suspend fun getByID(id : Long) : Movie? = api.getByID( id )

    suspend fun getByTitle(title : String) : List<Movie> = api.getByTitle( title )

    suspend fun searchMovie(query : String) : List<Movie> = api.searchMovie( query )

    suspend fun getNowPlaying() : List<Movie>{
        if(movieDataBase.listNowPlaying.isEmpty()){
            val response = api.getNowPlaying()
            movieDataBase.listNowPlaying = response
        }
        return  movieDataBase.listNowPlaying
    }

    suspend fun getPopular(page : Long) : List<Movie>{
        if (movieDataBase.listPopular.isEmpty()) {
            val response = api.getPopular(page)
            movieDataBase.listPopular = response
        }
        return movieDataBase.listPopular
    }

    fun getVisited() : List<Movie> = VisitedDB.listVisited
    fun setVisited(movie : Movie) = VisitedDB.add(movie)
}