package com.peakycoders.filmy.entities.models

data class Movie (
    val backdrop_path: String?,
    val id: Long,
    val original_language: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String?,
    val release_date: String = "",
    val runtime: Long = -1,
    val status: String,
    val tagline: String,
    val title: String,
    val vote_average: Double,
    val vote_count: Long
)
