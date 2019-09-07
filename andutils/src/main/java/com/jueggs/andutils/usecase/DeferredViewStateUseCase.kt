package com.jueggs.andutils.usecase

import com.jueggs.jutils.usecase.StateEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred

interface DeferredViewStateUseCase<TViewState> {
    operator fun invoke(scope: CoroutineScope): Deferred<com.jueggs.jutils.usecase.StateEvent<TViewState>>
}