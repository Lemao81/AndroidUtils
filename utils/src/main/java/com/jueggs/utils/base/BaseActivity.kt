package com.jueggs.utils.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jueggs.utils.R
import com.jueggs.utils.extension.setNavigationTransitions

abstract class BaseActivity<TView> : AppCompatActivity() where TView : BaseView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout())
        inject()
        presenter().view = self()

        initializeViews()
        setListeners()

        if (savedInstanceState == null)
            onInitialStart()
        else
            restoreState(savedInstanceState)

        setNavigationTransitions(getEnterTransition(), getExitTransition(), getReenterTransition(), getReturnTransition())
    }

    abstract fun layout(): Int
    abstract fun inject(): Unit?
    abstract fun presenter(): BasePresenter<TView>
    abstract fun self(): TView

    open fun initializeViews() {}
    open fun setListeners() {}

    open fun onInitialStart() {}
    open fun restoreState(savedInstanceState: Bundle) {}

    open fun getEnterTransition(): Int? = R.transition.fade
    open fun getExitTransition(): Int? = R.transition.fade
    open fun getReenterTransition(): Int? = R.transition.fade
    open fun getReturnTransition(): Int? = R.transition.fade
}