package com.jueggs.andutils.usecase

interface UseCaseWithParameter<TParameter> {
    suspend operator fun invoke(param: TParameter)
}