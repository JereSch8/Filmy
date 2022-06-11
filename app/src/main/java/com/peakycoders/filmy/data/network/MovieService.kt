package com.peakycoders.filmy.data.network

import com.peakycoders.filmy.entities.models.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieService {
    private val retrofit = Helper.getRetrofit()

    suspend fun getByID(id : Long) : Movie?{
        /*
            Ejecutamos la peticion en una corrutina, para que se ejecute en un hilo
            secundario así no traba el hilo de la UI y se evita un ANR
         */
        return withContext(Dispatchers.IO){
            val response = retrofit.create(MovieApiClient::class.java).getByID( id )
            response.body()
        }
    }

    suspend fun getByTitle(title : String) : List<Movie>{
        return withContext(Dispatchers.IO){
            val response = retrofit.create(MovieApiClient::class.java).getByTitle( title )

            response.body()?.results ?: emptyList()
        }
    }

    suspend fun getNowPlaying() : List<Movie>{
        return withContext(Dispatchers.IO){
            val response = retrofit.create(MovieApiClient::class.java).getNowPlaying()

            response.body()?.results ?: emptyList()
        }
    }

    suspend fun getPopular(page : Long) : List<Movie>{
        return withContext(Dispatchers.IO){
            val response = retrofit.create(MovieApiClient::class.java).getPopular(page)

            response.body()?.results ?: emptyList()
        }
    }

    suspend fun searchMovie(query : String) : List<Movie>{
        return withContext(Dispatchers.IO){
            val response = retrofit.create(MovieApiClient::class.java).searchMovie(query)

            response.body()?.results ?: emptyList()
        }
    }
}