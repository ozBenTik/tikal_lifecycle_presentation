package com.example.presentation_lifecycle

import androidx.compose.ui.graphics.Color


sealed class ListState(val numberOfItems: Int = 0) {

    object EMPTY: ListState()

    object LargeList : ListState(
       10
    )

    object SmallList : ListState(
       3
    )
}

data class ExtendedState(
    val nameState: SimpleState? = null,
    val emailState: SimpleState? = null,
    val listState: ListState? = null,
) {
    companion object {
        val EMPTY = ExtendedState()
    }
}