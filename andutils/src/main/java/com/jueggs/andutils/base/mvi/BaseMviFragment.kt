package com.jueggs.andutils.base.mvi

import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.*
import android.view.*
import com.hannesdorfmann.mosby3.mvi.*
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.jueggs.andutils.R
import com.jueggs.andutils.extension.setNavigationTransitions

abstract class BaseMviFragment<TView : MvpView, TPresenter : MviPresenter<TView, *>> : MviFragment<TView, TPresenter>() {

    override fun createPresenter(): TPresenter = presenter()
    abstract fun presenter(): TPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
        setNavigationTransitions(enterTransition(), exitTransition(), reenterTransition(), returnTransition())
    }

    open fun initialize() {}

    open fun enterTransition(): Int? = R.transition.fade
    open fun exitTransition(): Int? = R.transition.fade
    open fun reenterTransition(): Int? = R.transition.fade
    open fun returnTransition(): Int? = R.transition.fade

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(layout(), container, false)
    abstract fun layout(): Int

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initializeViews()

        if (savedInstanceState == null)
            onInitialStart()
        else
            restoreState(savedInstanceState)

        setListeners()
    }

    open fun onInitialStart() {}
    open fun restoreState(savedInstanceState: Bundle) {}

    open fun initializeViews() {}
    open fun setListeners() {}

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        val resId = optionsMenu()
        if (resId != null)
            inflater?.inflate(resId, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    open fun optionsMenu(): Int? = null
    override fun onOptionsItemSelected(item: MenuItem) = onMenuItemSelected(item.itemId) ?: super.onOptionsItemSelected(item)
    open fun onMenuItemSelected(id: Int): Boolean? = null

    protected fun addFragment(@IdRes containerViewId: Int, fragment: Fragment) = childFragmentManager.beginTransaction().add(containerViewId, fragment).commit()
    protected fun replaceFragment(@IdRes containerViewId: Int, fragment: Fragment) = childFragmentManager.beginTransaction().replace(containerViewId, fragment).commit()
}