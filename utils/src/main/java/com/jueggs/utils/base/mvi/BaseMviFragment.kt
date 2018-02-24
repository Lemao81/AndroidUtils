package com.jueggs.utils.base.mvi

import android.content.Context
import android.os.Bundle
import android.view.*
import com.hannesdorfmann.mosby3.mvi.*
import com.hannesdorfmann.mosby3.mvp.MvpView

abstract class BaseMviFragment<TView : MvpView, TPresenter : MviPresenter<TView, *>> : MviFragment<TView, TPresenter>() {
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        inject()
    }

    open fun inject(): Unit? = null
    override fun createPresenter(): TPresenter = presenter()
    abstract fun presenter(): TPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
    }

    open fun initialize() {}

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
}