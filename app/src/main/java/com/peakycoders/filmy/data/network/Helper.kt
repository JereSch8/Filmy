package com.peakycoders.filmy.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*
    Patron Singleton, lo utilizamos para devolver una unica instancia de Retrofit
    que es quien se encarga de realizar las peticiones HTTP al API de peliculas
 */
object Helper {
    const val baseURL = "https://api.themoviedb.org/"
    fun getRetrofit() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}