package com.jueggs.andutils.usecase

import com.jueggs.jutils.usecase.StateEvent

interface ViewStateUseCase<TViewState> {
    suspend operator fun invoke(): com.jueggs.jutils.usecase.StateEvent<TViewState>
}