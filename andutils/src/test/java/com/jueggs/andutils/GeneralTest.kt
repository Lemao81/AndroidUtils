package com.jueggs.andutils

import org.hamcrest.core.IsEqual.*
import org.junit.*
import org.junit.Assert.*

class GeneralTest {
    @Test
    fun test_that_error_messages_include_arguments() {
        var actual = ERROR_NO_EVENTHANDLER_ID.format("arg")
        var expected = "No variable id for eventhandler set. Override arg to set a binding variable id or remove eventhandler"
        assertThat(actual, equalTo(expected))

        actual = ERROR_NO_INCLUDED_LAYOUT.format("arg1", "arg2")
        expected = "No included layout found although announced. Set arg1 to false or include layout with id set to '@+id/arg2'"
        assertThat(actual, equalTo(expected))

        actual = ERROR_NO_SETVARIABLE_METHOD.format("arg")
        expected = "Method 'arg' not found, check generated binding class(es)"
        assertThat(actual, equalTo(expected))
    }
}