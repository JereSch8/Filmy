package com.peakycoders.filmy.data.repository

import com.peakycoders.filmy.data.database.CastDataBase
import com.peakycoders.filmy.data.network.CastService
import com.peakycoders.filmy.entities.models.Cast
import javax.inject.Inject

class CastRepository @Inject constructor(
    private val api : CastService,
    private val castDatabase : CastDataBase
){
    suspend fun getCasting(id : Long) : List<Cast> {
        if (castDatabase.listPopular[id].isNullOrEmpty() ){
            castDatabase.listPopular[id] = api.getCasting( id )
        }
        return castDatabase.listPopular[id]?.toList() ?: emptyList()
    }
}