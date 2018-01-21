package com.jueggs.utils.base

import android.content.Intent

class BaseActivityPresenter<TView : BaseView> : BasePresenter<TView>() {
    open fun getExtras(intent: Intent) {}
}