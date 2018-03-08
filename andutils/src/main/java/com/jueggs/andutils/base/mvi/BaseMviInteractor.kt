package com.jueggs.andutils.base.mvi

import io.reactivex.*

abstract class BaseMviInteractor<TViewState>(var viewState: TViewState) {

    fun updateViewState(state: TViewState): TViewState {
        viewState = state
        return viewState
    }

    fun just(state: TViewState): Observable<TViewState> = Observable.just(state)

    fun single(state: TViewState): Single<TViewState> = Single.just(state)
}