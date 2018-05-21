package com.jueggs.andutils.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.annotation.IdRes
import android.support.v4.app.*
import android.support.v7.widget.Toolbar
import android.view.*
import com.jueggs.andutils.*
import com.jueggs.andutils.extension.*
import com.jueggs.andutils.util.AppMode

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout())
        initialize()
        setToolbar(toolbar(), toolbarTitle(), toolbarNavigateBack())
        initializeViews()
        setListeners()
        setNavigationTransitions(enterTransition(), exitTransition(), reenterTransition(), returnTransition())

        if (savedInstanceState == null) {
            addFragments()
            onInitialStart()
        } else {
            onRecreated(savedInstanceState)
        }
    }

    abstract fun layout(): Int
    open fun initialize() {}

    private fun setToolbar(toolbar: View?, title: Int? = null, navigateBack: Boolean = true) {
        if (toolbar != null && toolbar is Toolbar) {
            setSupportActionBar(toolbar)
            if (title != null)
                supportActionBar?.title = getString(title)
            if (navigateBack) {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                supportActionBar?.setDisplayShowHomeEnabled(true)
            }
        }
    }

    open fun toolbar(): View? = null
    open fun toolbarTitle(): Int? = null
    open fun toolbarNavigateBack(): Boolean = true

    open fun initializeViews() {}
    open fun setListeners() {}

    open fun enterTransition(): Int? = R.transition.fade
    open fun exitTransition(): Int? = R.transition.fade
    open fun reenterTransition(): Int? = R.transition.fade
    open fun returnTransition(): Int? = R.transition.fade

    private fun addFragments() {
        if (AppMode.singlePane) {
            val singlePane = singlePaneFragment()
            if (singlePane != null) {
                val (id, fragment) = singlePane
                addFragment(id, fragment)
            }
        } else {
            val twoPane = twoPaneFragments()
            if (twoPane != null) {
                val (id1, fragment1) = twoPane.first
                val (id2, fragment2) = twoPane.second
                addFragment(id1, fragment1)
                addFragment(id2, fragment2)
            }
        }
    }

    open fun singlePaneFragment(): Pair<Int, Fragment>? = null
    open fun twoPaneFragments(): Pair<Pair<Int, Fragment>, Pair<Int, Fragment>>? = null

    open fun onInitialStart() {}
    open fun onRecreated(savedInstanceState: Bundle) {}

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
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        val backPressHandled = (supportFragmentManager.fragments.firstOrNull { it.isVisible } as? BackPressHandler)?.onBackPressed() ?: false
        if (!backPressHandled)
            super.onBackPressed()
    }

    fun addFragment(@IdRes containerViewId: Int, fragment: Fragment) = supportFragmentManager.beginTransaction().add(containerViewId, fragment).addToBackStack(fragment::class.simpleName).commit()
    fun replaceFragment(@IdRes containerViewId: Int, fragment: Fragment) = supportFragmentManager.beginTransaction().replace(containerViewId, fragment).commit()
    fun popFragment() = supportFragmentManager.popBackStack()

    inline fun <reified T> findFragment(@IdRes id: Int): T {
        val fragment = supportFragmentManager.findFragmentById(id)
        checkCast<T>(fragment)
        return fragment as T
    }

    inline fun <reified T> findFragment(tag: String): T {
        val fragment = supportFragmentManager.findFragmentByTag(tag)
        checkCast<T>(fragment)
        return fragment as T
    }

    fun toggleHomeAsUp(show: Boolean) = supportActionBar?.setDisplayHomeAsUpEnabled(show)
}