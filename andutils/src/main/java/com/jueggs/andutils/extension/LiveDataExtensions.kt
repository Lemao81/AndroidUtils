package com.jueggs.andutils.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.jueggs.andutils.observable.NonNullMediatorLiveData
import com.jueggs.andutils.util.SingleLiveEvent

fun <T> LiveData<T>.nonNull(): NonNullMediatorLiveData<T> {
    val mediator = NonNullMediatorLiveData<T>()
    mediator.addSource(this) { t -> t?.let { mediator.value = it } }
    return mediator
}

fun <T> NonNullMediatorLiveData<T>.observe(owner: LifecycleOwner, observer: (T) -> Unit) = this.observe(owner, Observer { it?.let(observer) })

fun MutableLiveData<Unit>.fire() {
    value = Unit
}

fun MutableLiveData<Unit>.post() = postValue(Unit)

fun MutableLiveData<Boolean>.fire(value: Boolean?) {
    this.value = value
}

fun MutableLiveData<Boolean>.post(value: Boolean?) = postValue(value)

fun MutableLiveData<Boolean>.fireTrue() {
    value = true
}

fun MutableLiveData<Boolean>.postTrue() = postValue(true)

fun MutableLiveData<Boolean>.fireFalse() {
    value = false
}

fun MutableLiveData<Boolean>.postFalse() = postValue(false)

fun MutableLiveData<Int>.fire(id: Int?) {
    value = id
}

fun MutableLiveData<Int>.post(id: Int?) = postValue(id)

fun <T> LiveData<T>.toSingleEvent(): LiveData<T> {
    val result = SingleLiveEvent<T>()
    result.addSource(this) {
        result.value = it
    }
    return result
}