package com.jueggs.andutils.usecase

import com.jueggs.andutils.aac.StateEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred

interface DeferredViewStateUseCaseWithParameter<TViewState, TParameter> {
    operator fun invoke(scope: CoroutineScope, param: TParameter): Deferred<StateEvent<TViewState>>
}