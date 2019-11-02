package com.jueggs.andutils.base

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlin.coroutines.CoroutineContext

abstract class BaseNavigationActivity : AppCompatActivity(), CoroutineScope {
    private val job = SupervisorJob()
    protected lateinit var navController: NavController
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
        val navHostFragmentResId = navHostFragment()
        checkNotNull(navHostFragmentResId)
        navController = findNavController(navHostFragmentResId)
        setupWithNavController()
        initialize()
        initializeViews()
        observeLiveData(this)
        setListeners()
        if (savedInstanceState == null) {
            onInitialStart()
        } else {
            restoreState(savedInstanceState)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu) = optionsMenu()?.let { menuInflater.inflate(it, menu); true } ?: super.onCreateOptionsMenu(menu)

    override fun onOptionsItemSelected(item: MenuItem) =
        onMenuItemSelected(item.itemId) ?: item.onNavDestinationSelected(navController) ?: super.onOptionsItemSelected(item)

    override fun onDestroy() {
        super.onDestroy()
        coroutineContext.cancelChildren()
    }

    abstract fun layout(): Int

    open fun bindingItems(): Map<Int, Any>? = null
    open fun initialize() {}
    open fun initializeViews() {}
    open fun navHostFragment(): Int? = null
    open fun appBarConfiguration(): AppBarConfiguration? = null
    open fun toolbar(): View? = null
    open fun navigationView(): View? = null
    open fun bottomNavigationView(): View? = null
    open fun observeLiveData(owner: LifecycleOwner) {}
    open fun setListeners() {}
    open fun onInitialStart() {}
    open fun restoreState(savedInstanceState: Bundle) {}
    open fun optionsMenu(): Int? = null
    open fun onMenuItemSelected(id: Int): Boolean? = null

    private fun setupWithNavController() {
        (toolbar() as? Toolbar)?.setupWithNavController(navController, appBarConfiguration() ?: AppBarConfiguration(navController.graph))
        (navigationView() as? NavigationView)?.setupWithNavController(navController)
        (bottomNavigationView() as? BottomNavigationView)?.setupWithNavController(navController)
    }
}