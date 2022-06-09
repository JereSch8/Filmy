package com.peakycoders.filmy.data.network

import com.peakycoders.filmy.entities.models.Cast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CastService {
    private val retrofit = Helper.getRetrofit()

    suspend fun getCasting(id : Long) : List<Cast>{
        return withContext(Dispatchers.IO){
            val response = retrofit.create(CastApiClient::class.java).getCasting( id )
            response.body()?.cast ?: emptyList()
        }
    }
}