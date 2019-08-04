package com.jueggs.andutils.base

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil.setContentView
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.jueggs.andutils.Util.checkCast
import com.jueggs.andutils.R
import com.jueggs.andutils.extension.setNavigationTransitions
import com.jueggs.andutils.interfaces.BackPressHandler
import com.jueggs.andutils.util.AppMode
import com.jueggs.jutils.Util.withNotNull
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlin.coroutines.CoroutineContext

abstract class BaseActivity : AppCompatActivity(), CoroutineScope {
    private val job = SupervisorJob()
    protected var waiterView: ConstraintLayout? = null
    var navController: NavController? = null

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bindingItems = bindingItems()
        if (bindingItems != null) {
            val binding = setContentView<ViewDataBinding>(this, layout())
            bindingItems.forEach { binding.setVariable(it.key, it.value) }
            binding.setLifecycleOwner(this)
        } else {
            setContentView(layout())
        }

        navHostFragment()?.let { navController = findNavController(it) }

        initialize()
        setToolbar(toolbar(), toolbarTitle(), toolbarBackNavigation(), toolbarHomeIcon())
        initializeViews()
        observeLiveData(this)
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
    open fun bindingItems(): Map<Int, Any>? = null
    open fun initialize() {}

    private fun setToolbar(toolbar: View?, toolbarTitle: Int?, navigateBack: Boolean, homeButtonResId: Int?) {
        if (toolbar != null && toolbar is Toolbar) {
            setSupportActionBar(toolbar)
            withNotNull(supportActionBar) {
                toolbarTitle?.let { title = getString(it) }
                if (navigateBack) {
                    setDisplayHomeAsUpEnabled(true)
                    setDisplayShowHomeEnabled(true)
                }
                homeButtonResId?.let {
                    setDisplayHomeAsUpEnabled(true)
                    setHomeAsUpIndicator(it)
                }
            }
        }
    }

    open fun toolbar(): View? = null
    open fun toolbarTitle(): Int? = null
    open fun toolbarBackNavigation(): Boolean = false
    open fun toolbarHomeIcon(): Int? = null

    open fun initializeViews() {}
    open fun observeLiveData(owner: LifecycleOwner) {}
    open fun setListeners() {}

    open fun navHostFragment(): Int? = null

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
        return navController?.navigateUp() ?: run {
            onBackPressed()
            return super.onSupportNavigateUp()
        }
    }

    override fun onBackPressed() {
        val backPressHandled = (supportFragmentManager.fragments.firstOrNull { it.isVisible } as? BackPressHandler)?.onBackPressed() ?: false
        if (!backPressHandled) {
            super.onBackPressed()
        }
    }

    fun addFragment(@IdRes containerViewId: Int, fragment: Fragment, addToBackStack: Boolean = true, tag: String? = null): Int {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(containerViewId, fragment, tag ?: fragment::class.simpleName)
        if (addToBackStack) {
            transaction.addToBackStack(tag ?: fragment::class.simpleName)
        }
        return transaction.commit()
    }

    fun attachFragment(fragment: Fragment) = supportFragmentManager.beginTransaction().attach(fragment).commit()

    fun attachOrAddFragment(@IdRes containerViewId: Int, fragment: Lazy<Fragment>, addToBackStack: Boolean = true, tag: String? = null): Int {
        val addedFragment = supportFragmentManager.findFragmentByTag(tag)
        return if (addedFragment != null) {
            attachFragment(addedFragment)
        } else {
            addFragment(containerViewId, fragment.value, addToBackStack, tag)
        }
    }

    fun detachFragment(fragment: Fragment) = supportFragmentManager.beginTransaction().detach(fragment).commit()

    fun replaceFragment(@IdRes containerViewId: Int, fragment: Fragment, tag: String? = null) = supportFragmentManager.beginTransaction().replace(containerViewId, fragment, tag).commit()

    fun popFragment() = supportFragmentManager.popBackStack()

    inline fun <reified T> findFragment(@IdRes id: Int): T? {
        val fragment = supportFragmentManager.findFragmentById(id)
        fragment?.let { checkCast<T>(it) }
        return fragment as? T
    }

    inline fun <reified T> findFragment(tag: String?): T? {
        val fragment = supportFragmentManager.findFragmentByTag(tag)
        fragment?.let { checkCast<T>(it) }
        return fragment as? T
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineContext.cancelChildren()
    }

    fun toggleHomeAsUp(show: Boolean) = supportActionBar?.setDisplayHomeAsUpEnabled(show)
}