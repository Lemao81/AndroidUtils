package com.jueggs.andutils.base.mvi

import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.*
import android.support.v7.widget.Toolbar
import android.view.*
import com.hannesdorfmann.mosby3.mvi.*
import com.hannesdorfmann.mosby3.mvp.*
import com.jueggs.andutils.R
import com.jueggs.andutils.extension.*

abstract class BaseMviActivity<TView : MvpView, TPresenter : MviPresenter<TView, *>> : MviActivity<TView, TPresenter>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout())
        initialize()
        setToolbar(toolbar(), toolbarTitle(), toolbarNavigateBack())
        initializeViews()
        setListeners()
        setNavigationTransitions(enterTransition(), exitTransition(), reenterTransition(), returnTransition())

        if (savedInstanceState == null)
            onInitialStart()
        else
            onRecreated(savedInstanceState)
    }

    abstract fun layout(): Int
    open fun initialize() {}

    fun setToolbar(toolbar: View?, title: Int? = null, shallNavigateBack: Boolean = true) {
        if (toolbar != null && toolbar is Toolbar) {
            setSupportActionBar(toolbar)
            if (title != null)
                supportActionBar?.title = getString(title)
            if (shallNavigateBack) {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                supportActionBar?.setDisplayShowHomeEnabled(true)
            }
        }
    }

    open fun toolbar(): View? = null
    open fun toolbarTitle(): Int? = null
    open fun toolbarNavigateBack(): Boolean = true

    override fun createPresenter(): TPresenter = presenter()
    abstract fun presenter(): TPresenter

    open fun initializeViews() {}
    open fun setListeners() {}

    open fun enterTransition(): Int? = R.transition.fade
    open fun exitTransition(): Int? = R.transition.fade
    open fun reenterTransition(): Int? = R.transition.fade
    open fun returnTransition(): Int? = R.transition.fade

    open fun onInitialStart() {}
    open fun onRecreated(savedInstanceState: Bundle) {}

    override fun onStart() {
        super.onStart()
        initialIntents()
    }

    open fun initialIntents() {}

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val resId = optionsMenu()
        if (resId != null) {
            menuInflater.inflate(resId, menu)
            return true
        }
        return super.onCreateOptionsMenu(menu)
    }

    open fun optionsMenu(): Int? = null

    override fun onOptionsItemSelected(item: MenuItem) = onMenuItemSelected(item.itemId) ?: super.onOptionsItemSelected(item)

    open fun onMenuItemSelected(id: Int): Boolean? = null

    override fun onSupportNavigateUp(): Boolean {
        if (toolbarNavigateBack()) onBackPressed()
        return super.onSupportNavigateUp()
    }

    protected fun addFragment(@IdRes containerViewId: Int, fragment: Fragment) = supportFragmentManager.beginTransaction().add(containerViewId, fragment).addToBackStack(fragment::class.simpleName).commit()
    protected fun replaceFragment(@IdRes containerViewId: Int, fragment: Fragment) = supportFragmentManager.beginTransaction().replace(containerViewId, fragment).commit()
    fun popFragment() = supportFragmentManager.popBackStack()
}