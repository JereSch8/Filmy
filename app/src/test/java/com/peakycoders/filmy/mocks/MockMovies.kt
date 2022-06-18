package com.peakycoders.filmy.mocks

import com.peakycoders.filmy.entities.models.Movie

object MockMovies{
    val movie0 = Movie(
        backdrop_path = "",
        id = 1,
        original_language = "ES" ,
        overview = "Es una fake movie",
        popularity = 100.toDouble(),
        poster_path = "",
        release_date = "2022-06-12",
        runtime = 1,
        status = "OK",
        tagline = "",
        title = "The Best APP",
        vote_average = 10.0,
        vote_count = 100000
    )

    val movie1 = Movie(
        backdrop_path = "",
        id = 232,
        original_language = "ES" ,
        overview = "Es una fake movie2",
        popularity = 100.toDouble(),
        poster_path = "",
        release_date = "2012-09-02",
        runtime = 1,
        status = "OK",
        tagline = "",
        title = "The Best APP",
        vote_average = 8.33,
        vote_count = 900
    )

    val movie2 = Movie(
        backdrop_path = "",
        id = 457,
        original_language = "EN" ,
        overview = "Es una fake movie3",
        popularity = 100.toDouble(),
        poster_path = "",
        release_date = "2009-01-09",
        runtime = 1,
        status = "OK",
        tagline = "",
        title = "The Best APP",
        vote_average = 7.99,
        vote_count = 1900
    )

    val movie3 = Movie(
        backdrop_path = "",
        id = 7389,
        original_language = "EN" ,
        overview = "Es una fake movie4",
        popularity = 100.toDouble(),
        poster_path = "",
        release_date = "2018-04-23",
        runtime = 10,
        status = "OK",
        tagline = "",
        title = "The Best APP",
        vote_average = 8.93,
        vote_count = 14301
    )

    val movie4 = Movie(
        backdrop_path = "",
        id = 7389,
        original_language = "FR" ,
        overview = "Es una fake movie5",
        popularity = 100.toDouble(),
        poster_path = "",
        release_date = "2008-08-02",
        runtime = 1,
        status = "OK",
        tagline = "",
        title = "The Best APP",
        vote_average = 9.93,
        vote_count = 101900
    )

    val listMovie = listOf(movie0,movie1,movie2,movie3,movie4)
}