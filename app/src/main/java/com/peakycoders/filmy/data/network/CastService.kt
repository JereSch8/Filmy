package com.peakycoders.filmy.data.network

import com.peakycoders.filmy.entities.models.Cast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CastService @Inject constructor(
    private val api : CastApiClient
) {
    suspend fun getCasting(id : Long) : List<Cast>{
        return withContext(Dispatchers.IO){
            val response = api.getCasting( id )
            response.body()?.cast ?: emptyList()
        }
    }
}