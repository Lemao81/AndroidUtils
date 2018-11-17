package com.jueggs.andutils.usecase

import com.jueggs.andutils.aac.StateEvent

interface UseCase<TViewState> {
    suspend fun execute(): StateEvent<TViewState>
}