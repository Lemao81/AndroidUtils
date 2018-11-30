package com.jueggs.andutils.usecase

import com.jueggs.andutils.aac.StateEvent

interface UseCaseWithParameter<TParameter, TViewState> {
    suspend operator fun invoke(param: TParameter): StateEvent<TViewState>
}