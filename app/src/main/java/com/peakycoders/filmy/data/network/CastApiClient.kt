package com.peakycoders.filmy.data.network

import com.peakycoders.filmy.entities.SecretValues
import com.peakycoders.filmy.entities.models.ResponseModelCast
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CastApiClient {
    @GET("/3/movie/{id}/credits?api_key=${SecretValues.api_key}&language=es-ES")
    suspend fun getCasting(@Path("id") id : Long) : Response<ResponseModelCast>
}