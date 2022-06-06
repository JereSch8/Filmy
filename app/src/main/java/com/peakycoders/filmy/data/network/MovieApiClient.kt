package com.peakycoders.filmy.data.network

import com.peakycoders.filmy.entities.SecretValues
import com.peakycoders.filmy.entities.models.Movie
import com.peakycoders.filmy.entities.models.ResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiClient {
    @GET("/3/movie/{id}?api_key=${SecretValues.api_key}&language=es-ES")
    suspend fun getByID(@Path("id") id : Long) : Response<Movie>

    @GET("/3/search/movie?api_key=${SecretValues.api_key}&language=es-ES&page=1")
    suspend fun getByTitle(@Query("query") query : String) :  Response<ResponseModel>

    @GET("/3/movie/now_playing?api_key=${SecretValues.api_key}&language=es-ES&page=1")
    suspend fun getNowPlaying() : Response<ResponseModel>

    @GET("/3/movie/popular?api_key=89ff0eb326c02c31d4019264722dc1ed&language=es-ES")
    suspend fun getPopular(@Query("page") page : Long) :  Response<ResponseModel>
}