package com.jueggs.andutils.usecase

import com.jueggs.andutils.aac.StateEvent

interface ViewStateUseCaseWithParameter<TViewState, TParameter> {
    suspend operator fun invoke(param: TParameter): StateEvent<TViewState>
}