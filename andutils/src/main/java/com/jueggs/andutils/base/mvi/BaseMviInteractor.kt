package com.jueggs.andutils.base.mvi

import io.reactivex.*

abstract class BaseMviInteractor<TViewState>(var viewState: TViewState) {
    fun justWithUpdate(state: TViewState): Observable<TViewState> = Observable.just(updateViewState(state))
    fun justWithUpdate(state1: TViewState, state2: TViewState): Observable<TViewState> = Observable.just(updateViewState(state1), updateViewState(state2))
    fun singleWithUpdate(state: TViewState): Single<TViewState> = Single.just(updateViewState(state))

    fun updateViewState(state: TViewState): TViewState {
        viewState = state
        return viewState
    }
}