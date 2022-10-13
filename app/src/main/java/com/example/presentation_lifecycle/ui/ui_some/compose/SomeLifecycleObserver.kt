package com.example.presentation_lifecycle.ui.ui_some.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

// A side-effect is a change to the state of the app that happens outside the scope of a composable function.

/**
 * A Composable that observes the lifecycle of the lifecycleOwner and able to trigger callbacks of it.
 * This is being recomposed when the lifecycleOwner is being changed
 * @param lifecycleOwner - the lifecycle owner to be followed
 * @param onResume - an example of a callback
 */
@Composable
fun LifecycleAwareComposable(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    onResume: () -> Unit
) {

    // onResume is a SideEffect.
    // Safely update the current lambda when a new one is provided.
    // Avoids recomposition (which results in the DisposableEffect restart) if the value changes.
    val currentOnResume by rememberUpdatedState(onResume)

    // A long-lived operation
    // This is being restarted if the key `lifecycleOwner` changes
    // When DisposableEffect leaves the composition, the `onDispose` is being called and the observer is being cleaned up.
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> println("Lifecycle Event - ON_CREATE")
                Lifecycle.Event.ON_START -> println("Lifecycle Event - ON_START")
                Lifecycle.Event.ON_PAUSE -> println("Lifecycle Event - ON_PAUSE")
                Lifecycle.Event.ON_STOP -> println("Lifecycle Event - ON_STOP")
                Lifecycle.Event.ON_DESTROY -> println("Lifecycle Event - ON_DESTROY")
                Lifecycle.Event.ON_ANY -> println("Lifecycle Event - ON_ANY")

                // Calls the remembered `currentOnResume` method
                Lifecycle.Event.ON_RESUME -> {
                    println("Lifecycle Event - ON_RESUME")
                    currentOnResume()
                }
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}