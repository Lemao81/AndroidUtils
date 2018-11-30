package com.jueggs.andutils.usecase

import com.jueggs.andutils.aac.StateEvent

interface UseCase<TViewState> {
    suspend operator fun invoke(): StateEvent<TViewState>
}