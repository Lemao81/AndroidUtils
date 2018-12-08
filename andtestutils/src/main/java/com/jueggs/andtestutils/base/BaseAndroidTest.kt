package com.jueggs.andtestutils.base

import android.app.Activity
import androidx.test.rule.ActivityTestRule
import com.agoda.kakao.Screen
import com.jueggs.andtestutils.rule.DisableAnimationsRule
import org.junit.ClassRule
import org.junit.Rule
import org.koin.dsl.context.ModuleDefinition
import org.koin.dsl.module.module
import org.koin.standalone.StandAloneContext.loadKoinModules
import org.koin.standalone.StandAloneContext.stopKoin
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

    fun reinitKoin(moduleDefinition: ModuleDefinition.() -> Unit) {
        stopKoin()
        loadKoinModules(module(definition = moduleDefinition))
    }

    companion object {
        @ClassRule
        @JvmField
        val disableAnimationsRule = DisableAnimationsRule()
    }
}