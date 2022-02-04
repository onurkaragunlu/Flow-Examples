package com.onurkaragunlu.getirflow.examples

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow


//Cold Flow and Hot Flow
fun main3() = runBlocking {

    val scope = CoroutineScope(Dispatchers.Default)

    val coldFlow = flow<Int> {
        emit(1)
        delay(1000)
        emit(2)
    }

    val hotFlow = MutableStateFlow(0)

    scope.launch {
        coldFlow.collect {
            println("Cold Flow: $it")
        }
    }

    delay(1000)
    hotFlow.value = 1
    delay(1000)
    hotFlow.value = 2

    scope.launch {
        hotFlow.collect {
            println("Hot Flow: $it")
        }
    }
    delay(1000)
    hotFlow.value = 3

    delay(10000)

}