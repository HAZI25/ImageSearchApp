package com.example.imagesearch.common

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*

fun <T> Flow<T>.likeStateFlow(
    scope: CoroutineScope,
    initialValue: T,
): StateFlow<T> = this.stateIn(
    scope,
    started = SharingStarted.WhileSubscribed(5_000),
    initialValue,
)

fun <T> Flow<T>.observe(lifecycleOwner: LifecycleOwner, action: suspend (value: T) -> Unit) {
    onEach(action)
        .flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
        .launchIn(lifecycleOwner.lifecycleScope)
}