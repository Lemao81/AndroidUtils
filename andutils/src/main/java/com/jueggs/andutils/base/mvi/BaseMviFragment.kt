package com.jueggs.andutils.base.mvi

import android.app.Activity
import android.content.Context
import android.databinding.*
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.*
import android.view.*
import com.hannesdorfmann.mosby3.mvi.*
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.jueggs.andutils.R
import com.jueggs.andutils.base.BackPressHandler
import com.jueggs.andutils.extension.setNavigationTransitions

abstract class BaseMviFragment<TView : MvpView, TPresenter : MviPresenter<TView, *>, TFragmentListener : Activity> : MviFragment<TView, TPresenter>(), BackPressHandler {
    protected var listener: TFragmentListener? = null
    protected var isInitialStart: Boolean = false

    override fun createPresenter(): TPresenter = presenter()
    abstract fun presenter(): TPresenter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as TFragmentListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) isInitialStart = true
        if (optionsMenu() != null) setHasOptionsMenu(true)
        setNavigationTransitions(enterTransition(), exitTransition(), reenterTransition(), returnTransition())
        initialize()
    }

    open fun initialize() {}

    open fun enterTransition(): Int? = R.transition.fade
    open fun exitTransition(): Int? = R.transition.fade
    open fun reenterTransition(): Int? = R.transition.fade
    open fun returnTransition(): Int? = R.transition.fade

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val bindingItems = bindingItems()

        return if (bindingItems != null) {
            val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater, layout(), container, false)
            bindingItems.forEach { binding.setVariable(it.key, it.value) }
            binding.root
        } else
            inflater.inflate(layout(), container, false)
    }

    abstract fun layout(): Int
    open fun bindingItems(): Map<Int, Any>? = null

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

    override fun onStart() {
        super.onStart()
        initialIntents()
    }

    open fun initialIntents() {}

    override fun onBackPressed() = false

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        val resId = optionsMenu()
        if (resId != null)
            inflater?.inflate(resId, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onDetach() {
        listener = null
        super.onDetach()
    }

    open fun optionsMenu(): Int? = null
    override fun onOptionsItemSelected(item: MenuItem) = onMenuItemSelected(item.itemId) ?: super.onOptionsItemSelected(item)
    open fun onMenuItemSelected(id: Int): Boolean? = null

    protected fun addFragment(@IdRes containerViewId: Int, fragment: Fragment) = childFragmentManager.beginTransaction().add(containerViewId, fragment).addToBackStack(fragment::class.simpleName).commit()
    protected fun replaceFragment(@IdRes containerViewId: Int, fragment: Fragment) = childFragmentManager.beginTransaction().replace(containerViewId, fragment).commit()
    fun popFragment() = childFragmentManager.popBackStack()
}