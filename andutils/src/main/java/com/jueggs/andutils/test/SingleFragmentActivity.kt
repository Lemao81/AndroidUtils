package com.jueggs.andutils.test

import android.os.Bundle
import android.support.annotation.RestrictTo
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup.LayoutParams.*
import android.widget.FrameLayout
import androidx.navigation.NavController
import com.jueggs.andutils.R

@RestrictTo(RestrictTo.Scope.TESTS)
class SingleFragmentActivity : AppCompatActivity() {
    private val rootLayout: FrameLayout by lazy {
        FrameLayout(this).apply {
            id = ID
            layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
            setTag(R.id.nav_controller_view_tag, NavController(this@SingleFragmentActivity))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(rootLayout)
    }

    fun setNavController(navController: NavController) = rootLayout.setTag(R.id.nav_controller_view_tag, navController)
    fun setFragment(fragment: Fragment) = supportFragmentManager.beginTransaction().add(ID, fragment).commit()
    fun replaceFragment(fragment: Fragment) = supportFragmentManager.beginTransaction().replace(ID, fragment).commit()

    companion object {
        const val ID = 1
    }
}