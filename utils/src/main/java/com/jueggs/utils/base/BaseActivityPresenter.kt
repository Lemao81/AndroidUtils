package com.jueggs.utils.base

import android.content.Intent

abstract class BaseActivityPresenter<TView : BaseView> : BasePresenter<TView>() {
    open fun getExtras(intent: Intent) {}
}