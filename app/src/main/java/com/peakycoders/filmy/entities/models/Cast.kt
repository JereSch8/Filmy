package com.peakycoders.filmy.entities.models

data class Cast (
    val adult: Boolean = false,
    val gender: Long = 0 ,
    val id: Long = 0,
    val known_for_department: String = "",
    val name: String = "",
    val original_name: String = "",
    val popularity: Double = 0.0,
    val profile_path: String = "",
    val castID: Long = 0,
    val character: String = "",
    val creditID: String = "",
    val order: Long = 0
)

