package com.jueggs.utils.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import com.jueggs.utils.R
import com.jueggs.utils.extension.setNavigationTransitions

abstract class BaseActivity<TView : BaseView> : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout())
        inject()
        setupToolbar()

        presenter().view = self()
        presenter().ctx = this
        presenter().getExtras(intent)
        presenter().initialize()
        initialize()
        presenter().initializeViews()
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

    abstract fun presenter(): BaseActivityPresenter<TView>
    abstract fun self(): TView

    open fun initialize() {}
    open fun initializeViews() {}
    open fun setListeners() {}

    open fun onInitialStart() {}
    open fun restoreState(savedInstanceState: Bundle) {}

    open fun getEnterTransition(): Int? = R.transition.fade
    open fun getExitTransition(): Int? = R.transition.fade
    open fun getReenterTransition(): Int? = R.transition.fade
    open fun getReturnTransition(): Int? = R.transition.fade

    override fun onSupportNavigateUp(): Boolean {
        if (shallToolbarNavigateBack()) onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        presenter().view = presenter().viewStub()
        presenter().ctx = application
        super.onDestroy()
    }
}