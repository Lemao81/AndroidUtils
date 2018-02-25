package com.jueggs.utils.base.mvi

import com.hannesdorfmann.mosby3.mvp.MvpView

interface BaseMviView<TViewState> : MvpView {
    fun render(state: TViewState)
}