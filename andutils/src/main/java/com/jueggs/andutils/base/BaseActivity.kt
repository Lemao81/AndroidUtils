package com.jueggs.andutils.base

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.jueggs.andutils.AppManager
import com.jueggs.andutils.Util.checkCast
import com.jueggs.andutils.interfaces.BackPressHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlin.coroutines.CoroutineContext

abstract class BaseActivity : AppCompatActivity(), CoroutineScope {
    private val job = SupervisorJob()
    protected var waiterView: ConstraintLayout? = null
    override val coroutineContext: CoroutineContext = Dispatchers.Default + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bindingItems = bindingItems()
        if (bindingItems != null) {
            val binding = DataBindingUtil.setContentView<ViewDataBinding>(this, layout())
            bindingItems.forEach { binding.setVariable(it.key, it.value) }
            binding.lifecycleOwner = this
        } else {
            setContentView(layout())
        }
        setupToolbar()
        initialize()
        initializeViews()
        observeLiveData(this)
        setListeners()
        if (savedInstanceState == null) {
            if (singlePaneFragment() != null || twoPaneFragments() != null) {
                addFragments()
            }
            onInitialStart()
        } else {
            restoreState(savedInstanceState)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu) = optionsMenu()?.let { menuInflater.inflate(it, menu); true } ?: super.onCreateOptionsMenu(menu)

    override fun onOptionsItemSelected(item: MenuItem) = onMenuItemSelected(item.itemId) ?: super.onOptionsItemSelected(item)

    override fun onSupportNavigateUp() = onBackPressedOrNavigateUp() || super.onSupportNavigateUp()

    override fun onBackPressed() {
        if (!onBackPressedOrNavigateUp()) {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineContext.cancelChildren()
    }

    abstract fun layout(): Int

    open fun bindingItems(): Map<Int, Any>? = null
    open fun initialize() {}
    open fun initializeViews() {}
    open fun toolbar(): View? = null
    open fun toolbarTitle(): Int? = null
    open fun toolbarBackNavigation(): Boolean = false
    open fun toolbarHomeIcon(): Int? = null
    open fun observeLiveData(owner: LifecycleOwner) {}
    open fun setListeners() {}
    open fun singlePaneFragment(): Pair<Int, Fragment>? = null
    open fun twoPaneFragments(): Pair<Pair<Int, Fragment>, Pair<Int, Fragment>>? = null
    open fun onInitialStart() {}
    open fun restoreState(savedInstanceState: Bundle) {}
    open fun optionsMenu(): Int? = null
    open fun onMenuItemSelected(id: Int): Boolean? = null

    fun addFragment(@IdRes containerViewId: Int, fragment: Fragment, addToBackStack: Boolean = true, tag: String? = null) = supportFragmentManager.beginTransaction().apply {
        add(containerViewId, fragment, tag ?: fragment::class.simpleName)
        if (addToBackStack) {
            addToBackStack(tag ?: fragment::class.simpleName)
        }
    }.commit()

    fun attachFragment(fragment: Fragment) = supportFragmentManager.beginTransaction().attach(fragment).commit()

    fun attachOrAddFragment(@IdRes containerViewId: Int, fragment: Lazy<Fragment>, addToBackStack: Boolean = true, tag: String? = null) =
        supportFragmentManager.findFragmentByTag(tag)?.let {
            attachFragment(it)
        } ?: addFragment(containerViewId, fragment.value, addToBackStack, tag)

    fun detachFragment(fragment: Fragment) = supportFragmentManager.beginTransaction().detach(fragment).commit()

    fun replaceFragment(@IdRes containerViewId: Int, fragment: Fragment, tag: String? = null) =
        supportFragmentManager.beginTransaction().replace(containerViewId, fragment, tag).commit()

    fun popFragment() = supportFragmentManager.popBackStack()

    inline fun <reified T> findFragment(@IdRes id: Int) = supportFragmentManager.findFragmentById(id)?.apply { checkCast<T>(this) }?.let { it as? T }

    inline fun <reified T> findFragment(tag: String?) = supportFragmentManager.findFragmentByTag(tag)?.apply { checkCast<T>(this) }?.let { it as? T }

    fun toggleHomeAsUp(isShouldShow: Boolean) = supportActionBar?.setDisplayHomeAsUpEnabled(isShouldShow)

    private fun setupToolbar() {
        val toolbar = toolbar() as? Toolbar
        when {
            toolbar != null -> {
                setSupportActionBar(toolbar)
                supportActionBar?.let { bar ->
                    toolbarTitle()?.let { title = getString(it) }
                    if (toolbarBackNavigation()) {
                        bar.setDisplayHomeAsUpEnabled(true)
                        bar.setDisplayShowHomeEnabled(true)
                    }
                    toolbarHomeIcon()?.let {
                        bar.setDisplayHomeAsUpEnabled(true)
                        bar.setHomeAsUpIndicator(it)
                    }
                }
            }
            toolbarTitle() != null || toolbarBackNavigation() || toolbarHomeIcon() != null ->
                throw UninitializedPropertyAccessException(
                    "calling ${BaseActivity::toolbarTitle.name}(), ${BaseActivity::toolbarBackNavigation.name}() or ${BaseActivity::toolbarHomeIcon.name}() " +
                        "has no effect without allocating a toolbar with ${BaseActivity::toolbar.name}()"
                )
        }
    }

    private fun addFragments() {
        if (AppManager.isSinglePane) {
            singlePaneFragment()?.let { addFragment(it.first, it.second) }
        } else {
            twoPaneFragments()?.let {
                addFragment(it.first.first, it.first.second)
                addFragment(it.second.first, it.second.second)
            }
        }
    }

    private fun onBackPressedOrNavigateUp(): Boolean {
        val backHandlingFragment = supportFragmentManager.fragments.firstOrNull { it.isVisible } as? BackPressHandler

        return backHandlingFragment?.handleBackPressed() ?: false
    }
}