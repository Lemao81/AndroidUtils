package com.jueggs.utils.base.mvi

import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v7.widget.Toolbar
import android.view.*
import com.hannesdorfmann.mosby3.mvi.*
import com.hannesdorfmann.mosby3.mvp.*
import com.jueggs.utils.R
import com.jueggs.utils.extension.*

abstract class BaseMviActivity<TView : MvpView, TPresenter : MviPresenter<TView, *>> : MviActivity<TView, TPresenter>() {

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout())
        inject()
        initialize()
        setupToolbar()

        if (savedInstanceState == null)
            onInitialStart()
        else
            onRecreated(savedInstanceState)

        initializeViews()
        setListeners()

        setNavigationTransitions(getEnterTransition(), getExitTransition(), getReenterTransition(), getReturnTransition())
    }

    abstract fun layout(): Int
    open fun inject(): Unit? = null
    open fun initialize() {}

    private fun setupToolbar() {
        val toolbar = toolbar()
        if (toolbar != null && toolbar is Toolbar) {
            setSupportActionBar(toolbar)
            supportActionBar?.title = getString(toolbarTitle())
            if (shallToolbarNavigateBack()) {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                supportActionBar?.setDisplayShowHomeEnabled(true)
            }
        }
    }

    abstract fun toolbar(): View?
    open fun toolbarTitle(): Int = R.string.empty_string
    open fun shallToolbarNavigateBack(): Boolean = true

    override fun createPresenter(): TPresenter = presenter()
    abstract fun presenter(): TPresenter

    open fun onInitialStart() {}
    open fun onRecreated(savedInstanceState: Bundle) {}
    open fun initializeViews() {}
    open fun setListeners() {}

    open fun getEnterTransition(): Int? = R.transition.fade
    open fun getExitTransition(): Int? = R.transition.fade
    open fun getReenterTransition(): Int? = R.transition.fade
    open fun getReturnTransition(): Int? = R.transition.fade

    @CallSuper
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val resId = optionsMenu()
        if (resId != null) {
            menuInflater.inflate(resId, menu)
            return true
        }
        return super.onCreateOptionsMenu(menu)
    }

    open fun optionsMenu(): Int? = null

    @CallSuper
    override fun onOptionsItemSelected(item: MenuItem) = onMenuItemSelected(item.itemId) ?: super.onOptionsItemSelected(item)

    open fun onMenuItemSelected(id: Int): Boolean? = null

    @CallSuper
    override fun onSupportNavigateUp(): Boolean {
        if (shallToolbarNavigateBack()) onBackPressed()
        return super.onSupportNavigateUp()
    }
}