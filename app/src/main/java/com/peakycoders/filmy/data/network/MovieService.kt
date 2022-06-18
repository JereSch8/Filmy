package com.peakycoders.filmy.data.network

import com.peakycoders.filmy.entities.models.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieService @Inject constructor(
    private val api : MovieApiClient
) {
    suspend fun getByID(id : Long) : Movie?{
        /*
            Ejecutamos la peticion en una corrutina, para que se ejecute en un hilo
            secundario así no traba el hilo de la UI y se evita un ANR
         */
        return withContext(Dispatchers.IO){
            val response = api.getByID( id )
            response.body()
        }
    }

    suspend fun getByTitle(title : String) : List<Movie>{
        return withContext(Dispatchers.IO){
            val response = api.getByTitle( title )

            response.body()?.results ?: emptyList()
        }
    }

    suspend fun getNowPlaying() : List<Movie>{
        return withContext(Dispatchers.IO){
            val response = api.getNowPlaying()

            response.body()?.results ?: emptyList()
        }
    }

    suspend fun getPopular(page : Long) : List<Movie>{
        return withContext(Dispatchers.IO){
            val response = api.getPopular(page)

            response.body()?.results ?: emptyList()
        }
    }

    suspend fun searchMovie(query : String) : List<Movie>{
        return withContext(Dispatchers.IO){
            val response = api.searchMovie(query)

            response.body()?.results ?: emptyList()
        }
    }
}