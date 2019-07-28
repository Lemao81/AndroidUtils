package com.jueggs.rxutils.observable

import com.jueggs.rxutils.extension.check
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

class CheckSingleObserver<T>(private val actual: SingleObserver<in T>, private val predicate: (T) -> Boolean) : SingleObserver<T>, Disposable {
    private var disposable: Disposable? = null

    override fun onSuccess(t: T) {
        if (predicate(t)) {
            actual.onSuccess(t)
        } else {
            actual.onError(Exception("${Single::class.simpleName}.${Single<T>::check.name} condition failed"))
        }
    }

    override fun onSubscribe(d: Disposable) {
        disposable = d
        actual.onSubscribe(this)
    }

    override fun onError(e: Throwable) = actual.onError(e)

    override fun isDisposed() = disposable?.isDisposed ?: true

    override fun dispose() {
        disposable?.dispose()
    }
}