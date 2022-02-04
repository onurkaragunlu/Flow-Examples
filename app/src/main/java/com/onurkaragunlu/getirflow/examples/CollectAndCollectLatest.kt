package com.onurkaragunlu.getirflow.examples

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest


//Collect vs Collect Latest
fun main() = runBlocking {

    val scope = CoroutineScope(Dispatchers.Default)
    val stateFlow = MutableStateFlow(0)

    scope.launch {
        stateFlow.collectLatest {
            //Heavy Work
            delay(2000)
            println(it)

        }
    }
    delay(1000)
    stateFlow.value = 1
    delay(1000)
    stateFlow.value = 2



    delay(10000)
}