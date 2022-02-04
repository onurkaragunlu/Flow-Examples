package com.onurkaragunlu.getirflow.examples

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect


fun main() = runBlocking {
    val scope = CoroutineScope(Dispatchers.Default)
    val stateFlow = MutableStateFlow<Result>(Result.Loading)

    scope.launch {
        stateFlow.collect {
            println(it.javaClass.name.toString())
        }
    }


    delay(1000)
    stateFlow.value = Result.Car("BMW", 2022)
    delay(1000)
    stateFlow.value = Result.Car("BMW", 2022)

    delay(1000)
    stateFlow.value = Result.Error(errorCode = 1)
    delay(1000)
    stateFlow.value = Result.Error(errorCode = 1)



    delay(100000)
}

sealed class Result {
    object Loading : Result()
    class Car(val model: String, val year: Int) : Result()
    class Error(errorCode: Int) : Result()
}


