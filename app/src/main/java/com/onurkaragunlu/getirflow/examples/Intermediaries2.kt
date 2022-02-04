package com.onurkaragunlu.getirflow.examples

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

fun main2() = runBlocking {
    val scope = CoroutineScope(Dispatchers.Default)

    val flow = flow {
        emit(1)
        delay(1000)
        emit(2)
        delay(1000)
        throw  IllegalAccessError("Error")
    }

    scope.launch {
        flow.catch {
            println(it.localizedMessage)
        }.collect {
            println(it)
        }
    }
    delay(10000)
}