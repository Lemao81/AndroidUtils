package com.jueggs.utils.base.mvi

import io.reactivex.Observable

abstract class BaseMviInteractor<TviewState>(var viewState: TviewState) {
    fun updateViewState(state: TviewState): Observable<TviewState> {
        viewState = state
        return Observable.just(viewState)
    }

    fun just(state: TviewState): Observable<TviewState> = Observable.just(state)
}