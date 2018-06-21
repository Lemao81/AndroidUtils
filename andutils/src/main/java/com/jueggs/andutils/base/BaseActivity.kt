package com.jueggs.andutils.base

import android.databinding.*
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

        val bindingItems = bindingItems()
        if (bindingItems != null) {
            val binding = DataBindingUtil.setContentView<ViewDataBinding>(this, layout())
            bindingItems.forEach { binding.setVariable(it.key, it.value) }
            binding.setLifecycleOwner(this)
        } else
            setContentView(layout())

        initialize()
        setToolbar(toolbar(), toolbarTitle(), toolbarNavigateBack())
        initializeViews()
        setListeners()
        setObservers()
        setNavigationTransitions(enterTransition(), exitTransition(), reenterTransition(), returnTransition())

        if (savedInstanceState == null) {
            addFragments()
            onInitialStart()
        } else {
            onRecreated(savedInstanceState)
        }
    }

    abstract fun layout(): Int
    open fun bindingItems(): Map<Int, Any>? = null
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
    open fun setObservers() {}

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

    fun addFragment(@IdRes containerViewId: Int, fragment: Fragment, addToBackStack: Boolean = true, tag: String? = null): Int {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(containerViewId, fragment, tag ?: fragment::class.simpleName)
        if (addToBackStack)
            transaction.addToBackStack(tag ?: fragment::class.simpleName)
        return transaction.commit()
    }

    fun attachFragment(fragment: Fragment) = supportFragmentManager.beginTransaction().attach(fragment).commit()

    fun attachOrAddFragment(@IdRes containerViewId: Int, fragment: Lazy<Fragment>, addToBackStack: Boolean = true, tag: String? = null): Int {
        val addedFragment = supportFragmentManager.findFragmentByTag(tag)
        return if (addedFragment != null) attachFragment(addedFragment) else addFragment(containerViewId, fragment.value, addToBackStack, tag)
    }

    fun detachFragment(fragment: Fragment) = supportFragmentManager.beginTransaction().detach(fragment).commit()

    fun replaceFragment(@IdRes containerViewId: Int, fragment: Fragment, tag: String? = null) = supportFragmentManager.beginTransaction().replace(containerViewId, fragment, tag).commit()

    fun popFragment() = supportFragmentManager.popBackStack()

    inline fun <reified T> findFragment(@IdRes id: Int): T {
        val fragment = supportFragmentManager.findFragmentById(id)
        checkCast<T>(fragment)
        return fragment as T
    }

    inline fun <reified T> findFragment(tag: String?): T {
        val fragment = supportFragmentManager.findFragmentByTag(tag)
        checkCast<T>(fragment)
        return fragment as T
    }

    fun toggleHomeAsUp(show: Boolean) = supportActionBar?.setDisplayHomeAsUpEnabled(show)
}