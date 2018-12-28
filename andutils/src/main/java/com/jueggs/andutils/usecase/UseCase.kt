package com.jueggs.andutils.usecase

import com.jueggs.andutils.aac.StateEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred

interface UseCase<TViewState> {
    operator fun invoke(scope: CoroutineScope): Deferred<StateEvent<TViewState>>
}