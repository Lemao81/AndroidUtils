package com.jueggs.andutils.usecase

import com.jueggs.jutils.usecase.StateEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred

interface DeferredViewStateUseCaseWithParameter<TViewState, TParameter> {
    operator fun invoke(scope: CoroutineScope, param: TParameter): Deferred<com.jueggs.jutils.usecase.StateEvent<TViewState>>
}