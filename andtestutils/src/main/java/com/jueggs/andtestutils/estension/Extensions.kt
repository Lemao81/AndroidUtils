package com.jueggs.andtestutils.estension

import android.app.Activity
import android.support.annotation.StringRes
import android.support.test.espresso.Espresso.*
import android.support.test.espresso.assertion.ViewAssertions.*
import android.support.test.espresso.matcher.RootMatchers.*
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import org.hamcrest.CoreMatchers.*

fun <T : Activity> ActivityTestRule<T>.assertToastIsDisplayed(@StringRes resId: Int) {
    onView(withText(resId)).inRoot(withDecorView(not(`is`(this.activity.window.decorView)))).check(matches(isDisplayed()))
}