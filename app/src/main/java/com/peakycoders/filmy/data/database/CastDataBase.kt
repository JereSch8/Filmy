package com.peakycoders.filmy.data.database

import com.peakycoders.filmy.entities.models.Cast
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CastDataBase @Inject constructor() {
    var listPopular = hashMapOf<Long,List<Cast>>()
}