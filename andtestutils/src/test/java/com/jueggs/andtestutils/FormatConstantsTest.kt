package com.jueggs.andtestutils

import org.junit.Assert.assertEquals
import org.junit.Test

class FormatConstantsTest {
    @Test
    fun `test that values are inserted properly`() {
        val expectedTransitionAnimation = "settings put global transition_animation_scale 1"
        val expectedWindowAnimation = "settings put global window_animation_scale 1"
        val expectedAnimatorDuration = "settings put global animator_duration_scale 1"

        assertEquals(expectedTransitionAnimation, String.format(SHELL_TRANSITION_ANIMATION_SCALE_FORMAT, 1))
        assertEquals(expectedWindowAnimation, String.format(SHELL_WINDOW_ANIMATION_SCALE_FORMAT, 1))
        assertEquals(expectedAnimatorDuration, String.format(SHELL_ANIMATOR_DURATION_SCALE_FORMAT, 1))
    }
}