package com.peakycoders.filmy.entities.models

import kotlinx.serialization.*

@Serializable
data class Movie (
    @SerialName("backdrop_path")
    val backdropPath: String?,
    val id: Long,

    @SerialName("original_language")
    val originalLanguage: String,
    val overview: String,
    val popularity: Double,

    @SerialName("poster_path")
    val posterPath: String?,

    @SerialName("release_date")
    val releaseDate: String = "",

    val runtime: Long = -1,

    val status: String,
    val tagline: String,
    val title: String,

    @SerialName("vote_average")
    val voteAverage: Double,

    @SerialName("vote_count")
    val voteCount: Long
)
