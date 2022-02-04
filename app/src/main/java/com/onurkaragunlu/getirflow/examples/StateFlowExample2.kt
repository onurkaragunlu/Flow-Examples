package com.onurkaragunlu.getirflow.examples

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect

fun main() = runBlocking {
    val scope = CoroutineScope(Dispatchers.Default)
    val stateFlow = MutableStateFlow(0)
    scope.launch {
        stateFlow.collect {
            println(it)
        }
    }

    for (i in 1..10_000) {
        stateFlow.value = i
    }

    delay(100000)
}