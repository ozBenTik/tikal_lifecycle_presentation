package com.example.presentation_lifecycle

import androidx.compose.ui.graphics.Color

sealed class SimpleState(
    val text: String = "",
    val textColor: Color = Color.White,
    val backgroundColor: Color = Color.White
) {

    object EMPTY: SimpleState()

    object State1 : SimpleState(
        "Oh Yeah!",
        textColor = Color.DarkGray,
        backgroundColor = Color.Yellow
    )

    object State2 : SimpleState(
        "This is a presentation",
        textColor = Color.Red,
        backgroundColor = Color.DarkGray
    )

    object State3 :
        SimpleState(
            "Thank you",
            textColor = Color.DarkGray,
            backgroundColor = Color.Magenta
        )
}