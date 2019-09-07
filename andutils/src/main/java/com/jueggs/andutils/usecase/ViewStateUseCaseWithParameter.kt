package com.jueggs.andutils.usecase

import com.jueggs.jutils.usecase.StateEvent

interface ViewStateUseCaseWithParameter<TViewState, TParameter> {
    suspend operator fun invoke(param: TParameter): com.jueggs.jutils.usecase.StateEvent<TViewState>
}