package com.onurkaragunlu.getirflow

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow

object Event {
    val sharedFlow = MutableSharedFlow<String>(
        replay = 0,
        extraBufferCapacity = 3,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    )

    suspend fun sendEvent(value: String) {
        sharedFlow.emit(value)
    }
}