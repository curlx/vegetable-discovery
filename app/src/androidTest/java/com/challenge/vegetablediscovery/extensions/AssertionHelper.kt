package com.challenge.vegetablediscovery.extensions

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