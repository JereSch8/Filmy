package com.peakycoders.filmy.mocks

import com.peakycoders.filmy.entities.models.Cast


object MockCast{
    val cast0 = Cast(
        name = "Brayan Maier",
        original_name = "Brian Gerard",
        id = 0,
        castID = 0
    )

    val cast1 = Cast(
        name = "Franklin",
        original_name = "Franco Viotti",
        id = 1,
        castID = 1
    )

    val cast2 = Cast(
        name = "Manolito",
        original_name = "Emanuel Rodriguez",
        id = 2,
        castID = 2
    )

    val cast8 = Cast(
        name = "Jerecito",
        original_name = "Jeremias Schneider",
        id = 8,
        castID = 8
    )

    val listCast = listOf(cast0, cast1, cast2, cast8)
}