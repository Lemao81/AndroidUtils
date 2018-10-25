package com.jueggs.andtestutils.base

import android.app.Activity
import android.support.test.rule.ActivityTestRule
import com.agoda.kakao.Screen
import org.junit.Rule
import kotlin.reflect.KClass

abstract class BaseAndroidTest<TActivity : Activity, TScreen : Screen<*>>(activityClass: KClass<TActivity>, screenFactory: () -> TScreen, initialTouchMode: Boolean = true,
                                                                          launchActivity: Boolean = true) {
    @get:Rule
    val activityRule = ActivityTestRule<TActivity>(activityClass.java, initialTouchMode, launchActivity)
    val activity: TActivity
        get() = activityRule.activity
    val screen = screenFactory()
}