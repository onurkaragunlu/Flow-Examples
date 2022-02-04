package com.onurkaragunlu.getirflow.examples

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow


fun main1() = runBlocking {

    val scope = CoroutineScope(Dispatchers.Default)

    val flow = flow {
        emit(1)
        delay(1000)
        emit(2)
        delay(1000)
        emit(3)
    }


    scope.launch {
        flow.filter {
            delay(100)
            it > 2
        }.collect {
            println("Flow  : $it")
        }
    }

    delay(10000)

}