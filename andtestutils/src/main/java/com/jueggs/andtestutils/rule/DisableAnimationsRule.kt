package com.jueggs.andtestutils.rule

import android.support.test.InstrumentationRegistry
import android.support.test.uiautomator.UiDevice
import com.jueggs.andtestutils.*
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class DisableAnimationsRule : TestRule {
    override fun apply(base: Statement?, description: Description?): Statement = object : Statement() {
        override fun evaluate() {
            disableAnimations()
            try {
                base?.evaluate()
            } finally {
                enableAnimations()
            }
        }
    }

    private fun disableAnimations() {
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).executeShellCommand(String.format(SHELL_TRANSITION_ANIMATION_SCALE_FORMAT, 0))
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).executeShellCommand(String.format(SHELL_WINDOW_ANIMATION_SCALE_FORMAT, 0))
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).executeShellCommand(String.format(SHELL_ANIMATOR_DURATION_SCALE_FORMAT, 0))
    }

    private fun enableAnimations() {
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).executeShellCommand(String.format(SHELL_TRANSITION_ANIMATION_SCALE_FORMAT, 1))
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).executeShellCommand(String.format(SHELL_WINDOW_ANIMATION_SCALE_FORMAT, 1))
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).executeShellCommand(String.format(SHELL_ANIMATOR_DURATION_SCALE_FORMAT, 1))
    }
}