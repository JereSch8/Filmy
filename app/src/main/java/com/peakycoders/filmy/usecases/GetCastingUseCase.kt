package com.peakycoders.filmy.usecases

import com.peakycoders.filmy.data.repository.CastRepository
import com.peakycoders.filmy.entities.models.Cast
import javax.inject.Inject

class GetCastingUseCase @Inject constructor(
    private val repository: CastRepository
){
    suspend operator fun invoke(id : Long) : List<Cast> = repository.getCasting(id)
}