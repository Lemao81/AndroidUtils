package com.jueggs.andtestutils.rule

import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class RetryOnFailureRule : TestRule {
    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            override fun evaluate() {
                val retryCount = description.annotations.filterIsInstance<RetryOnFailure>().firstOrNull()?.count ?: 0

                var throwable: Throwable? = null
                repeat(retryCount + 1) {
                    runCatching { base.evaluate() }
                        .onSuccess { return }
                        .onFailure { t -> throwable = t }
                }

                throwable?.let { throw it }
            }
        }
    }
}