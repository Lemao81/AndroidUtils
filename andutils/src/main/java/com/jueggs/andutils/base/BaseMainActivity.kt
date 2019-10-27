package com.jueggs.andutils.base

import android.os.Bundle
import android.view.View
import com.jueggs.andutils.AppManager
import com.jueggs.andutils.R

abstract class BaseMainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // add layout R.id.checkTwoPane to the main layout variation inflated in two pane mode
        AppManager.isSinglePane = findViewById<View>(R.id.checkTwoPane) == null
    }
}