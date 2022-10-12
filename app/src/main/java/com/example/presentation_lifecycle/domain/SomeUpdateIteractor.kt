package com.example.presentation_lifecycle.domain

import com.example.presentation_lifecycle.data.SomeRepository
import com.example.presentation_lifecycle.model.SomeData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class SomeUpdateIteractor @Inject constructor(
    val repository: SomeRepository
) {

    fun fetch(): Flow<List<SomeData>> =
        repository.getSomeData().onEach {
            repository.saveSomeData(it)
        }
}