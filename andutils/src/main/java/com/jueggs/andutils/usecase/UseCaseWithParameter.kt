package com.jueggs.andutils.usecase

import com.jueggs.andutils.aac.StateEvent

interface UseCaseWithParameter<TParameter, TViewState> {
    suspend fun execute(param: TParameter): StateEvent<TViewState>
}