package com.peakycoders.filmy.data.database

import com.peakycoders.filmy.entities.models.Cast

class CastDataBase {
    companion object{
        var listPopular = hashMapOf<Long,List<Cast>>()
    }
}