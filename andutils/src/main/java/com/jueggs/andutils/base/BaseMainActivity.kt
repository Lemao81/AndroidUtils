package com.jueggs.andutils.base

import android.os.Bundle
import android.view.View
import com.jueggs.andutils.R
import com.jueggs.andutils.util.AppMode

abstract class BaseMainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (findViewById<View>(R.id.checkTwoPane) != null)
            AppMode.twoPane = true
    }
}