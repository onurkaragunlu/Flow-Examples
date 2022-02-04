package com.onurkaragunlu.getirflow.examples

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow

fun main9() = runBlocking {
    val scope1 = CoroutineScope(Dispatchers.Default)
    val scope2 = CoroutineScope(Dispatchers.Default)
    val coldFlow = flow<Int> {
        delay(3000)
        emit(2)
    }

    val sharedFlow = MutableSharedFlow<String>(replay = 0, extraBufferCapacity = 1)
    delay(1000)
    sharedFlow.emit("A")

    scope1.launch {
        sharedFlow.collect {
            println("Scope 1 : $it ")
        }
    }

    scope2.launch {
        sharedFlow.collect {
            println("Scope 2 : $it ")
        }
    }


    delay(1000)
    sharedFlow.emit("B")
    delay(1000)
    sharedFlow.emit("C")


    delay(10_000)


}


