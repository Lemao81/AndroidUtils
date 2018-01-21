package com.jueggs.utils.base

import android.os.Bundle
import android.support.v4.app.FragmentActivity

abstract class BaseFragmentPresenter<TView : BaseView> : BasePresenter<TView>() {
    var activity: FragmentActivity? = null

    open fun getArguments(bundle: Bundle) {}
}