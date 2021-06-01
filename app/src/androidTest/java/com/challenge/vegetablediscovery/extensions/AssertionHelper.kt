package com.challenge.vegetablediscovery.extensions

import androidx.annotation.IntRange
import com.agoda.kakao.common.assertions.BaseAssertions

fun waitUntil(millisecond: Long, retryIntervalInMillisecond: Long = 100L, assertion: () -> Unit) {
    var hasException: Boolean = true
    var exception: Throwable? = null
    var counter: Long = 0L
    while (hasException && counter < millisecond) {
        try {
            assertion.invoke()
            hasException = false
        } catch (e: Throwable) {
            exception = e
            Thread.sleep(retryIntervalInMillisecond)
            counter += retryIntervalInMillisecond
        }
    }
    if (hasException && exception != null) {
        throw exception
    }
}

fun BaseAssertions.isVisibleWithin(@IntRange(from = 100L) millisecond: Long) {
    waitUntil(millisecond) { isVisible() }
}