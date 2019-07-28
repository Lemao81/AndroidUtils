package com.jueggs.rxutils.extension

import com.google.android.gms.tasks.Task
import com.jueggs.rxutils.observable.CheckObservable
import com.jueggs.rxutils.observable.CheckSingle
import com.log4k.d
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.disposables.DisposableContainer
import io.reactivex.schedulers.Schedulers

fun io.reactivex.disposables.Disposable.disposedBy(container: DisposableContainer): Boolean = container.add(this)

fun <T> Observable<T>.scheduleIoMain(): Observable<T> = subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

fun <T> Observable<T>.scheduleCompMain(): Observable<T> = subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread())

fun <T> Observable<T>.observeOnMain(): Observable<T> = observeOn(AndroidSchedulers.mainThread())

fun <T> Observable<T>.logError(): Observable<T> = doOnError { d(it.message ?: "", it) }

fun <T> Observable<T>.check(predicate: (T) -> Boolean): Observable<T> = CheckObservable(this, predicate)

fun <T> Single<T>.check(predicate: (T) -> Boolean): Single<T> = CheckSingle(this, predicate)

fun <T> Completable.continueWith(next: T): Single<T> = Single.create { emitter ->
    subscribe({ emitter.onSuccess(next) }, emitter::onError)
}

fun <T> Task<T>.asSingle(): Single<T> = Single.create { emitter ->
    addOnSuccessListener(emitter::onSuccess)
    addOnFailureListener(emitter::onError)
}