package com.peakycoders.filmy.usecases

import com.peakycoders.filmy.data.repository.CastRepository
import com.peakycoders.filmy.entities.models.Cast

class GetCastingUseCase {
    suspend operator fun invoke(id : Long) : List<Cast> = CastRepository().getCasting(id)
}