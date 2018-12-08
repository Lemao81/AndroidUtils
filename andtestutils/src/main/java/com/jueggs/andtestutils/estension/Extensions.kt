package com.jueggs.andtestutils.estension

import android.app.Activity
import androidx.annotation.StringRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.not

fun <T : Activity> ActivityTestRule<T>.assertToastIsDisplayed(@StringRes resId: Int) {
    onView(withText(resId)).inRoot(withDecorView(not(`is`(this.activity.window.decorView)))).check(matches(isDisplayed()))
}