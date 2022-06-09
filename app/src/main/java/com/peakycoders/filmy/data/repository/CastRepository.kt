package com.peakycoders.filmy.data.repository

import com.peakycoders.filmy.data.database.CastDataBase
import com.peakycoders.filmy.data.network.CastService
import com.peakycoders.filmy.entities.models.Cast

class CastRepository {
    private val api = CastService()

    suspend fun getCasting(id : Long) : List<Cast> {
        if (CastDataBase.listPopular[id].isNullOrEmpty() ){
            CastDataBase.listPopular[id] = api.getCasting( id )
        }
        return CastDataBase.listPopular[id]?.toList() ?: emptyList()
    }
}