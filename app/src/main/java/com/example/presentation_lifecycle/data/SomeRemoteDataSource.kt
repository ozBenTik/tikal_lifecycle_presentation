package com.example.presentation_lifecycle.data

import androidx.compose.ui.graphics.Color
import com.example.presentation_lifecycle.model.SomeData
import kotlinx.coroutines.delay
import javax.inject.Inject
import kotlin.random.Random

class SomeRemoteDataSource @Inject constructor() {

    private val randomColor: Color
        get() = mutableListOf(Color.Blue, Color.Red, Color.Magenta, Color.LightGray).random()

    private val randomSize: Int
        get() = mutableListOf(5, 7, 3, 9).random()

    private val randomAge: Int
        get() = Random.nextInt(18, 35)

    suspend fun getData(): List<SomeData> =
        mutableListOf<SomeData>().apply {
            (1..randomSize).forEach {
                delay(200)
                val name = Char(it + 64).toString()
                add(SomeData(name, "My name is $name", randomAge, color = randomColor))
            }
        }.toList()


}