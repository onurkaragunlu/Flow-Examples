package com.onurkaragunlu.getirflow.examples

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val scope = CoroutineScope(Dispatchers.Default)

    val coldFlow = flow<Int> {
        emit(1)
        delay(1000)
        emit(2)
    }

    val stateFlow = coldFlow.stateIn(
        scope = scope,
        started = SharingStarted.Eagerly,
        initialValue = 1
    )

    val shareFlow = coldFlow.shareIn(
        scope = scope,
        started = SharingStarted.Lazily,
        1
    )

    val shareFlow2 = coldFlow.shareIn(
        scope = scope,
        started = SharingStarted.WhileSubscribed(5000),
        1
    )


}