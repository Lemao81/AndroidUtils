package com.jueggs.andtestutils.base

import android.app.Activity
import androidx.test.rule.ActivityTestRule
import com.agoda.kakao.Screen
import com.jueggs.andtestutils.rule.DisableAnimationsRule
import org.junit.ClassRule
import org.junit.Rule
import org.koin.core.context.loadKoinModules
import org.koin.core.context.stopKoin
import org.koin.dsl.ModuleDeclaration
import org.koin.dsl.module
import kotlin.reflect.KClass

abstract class BaseAndroidTest<TActivity : Activity, TScreen : Screen<*>>(
    activityClass: KClass<TActivity>,
    screenFactory: () -> TScreen,
    initialTouchMode: Boolean = true,
    launchActivity: Boolean = true
) {
    @get:Rule
    val activityRule = ActivityTestRule<TActivity>(activityClass.java, initialTouchMode, launchActivity)
    val screen = screenFactory()
    val activity: TActivity
        get() = activityRule.activity

    fun reinitKoin(moduleDeclaration: ModuleDeclaration) {
        stopKoin()
        loadKoinModules(module(moduleDeclaration = moduleDeclaration))
    }

    companion object {
        @ClassRule
        @JvmField
        val disableAnimationsRule = DisableAnimationsRule()
    }
}