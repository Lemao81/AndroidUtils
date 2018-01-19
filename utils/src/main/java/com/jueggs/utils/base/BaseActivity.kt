package com.jueggs.utils.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jueggs.utils.R
import com.jueggs.utils.extension.setNavigationTransitions

abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        injectDependencies()

        initializeComponents()
        initializeListeners()

        if (savedInstanceState == null)
            onInitialStart()
        else
            restoreState(savedInstanceState)

        setNavigationTransitions(getEnterTransition(), getExitTransition(), getReenterTransition(), getReturnTransition())
    }

    abstract fun getLayout(): Int

    open fun injectDependencies() {}

    open fun initializeComponents() {}
    open fun initializeListeners() {}

    open fun onInitialStart() {}
    open fun restoreState(savedInstanceState: Bundle) {}

    open fun getEnterTransition(): Int? = R.transition.fade
    open fun getExitTransition(): Int? = R.transition.fade
    open fun getReenterTransition(): Int? = R.transition.fade
    open fun getReturnTransition(): Int? = R.transition.fade
}