package com.jueggs.andutils.usecase

import com.jueggs.andutils.aac.StateEvent

interface ViewStateUseCase<TViewState> {
    suspend operator fun invoke(): StateEvent<TViewState>
}