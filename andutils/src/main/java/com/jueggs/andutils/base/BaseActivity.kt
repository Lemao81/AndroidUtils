package com.jueggs.andutils.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (layout() != null) setContentView(layout()!!)
        initialize()
    }

    open fun layout(): Int? = null

    open fun initialize() {}
}