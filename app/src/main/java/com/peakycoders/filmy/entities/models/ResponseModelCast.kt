package com.peakycoders.filmy.entities.models

data class ResponseModelCast (
    val id: Long,
    val cast: List<Cast>,
    val crew: List<Cast>
)
