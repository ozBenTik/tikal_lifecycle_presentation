package com.example.presentation_lifecycle.domain

import com.example.presentation_lifecycle.data.SomeRepository
import com.example.presentation_lifecycle.model.SomeData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SomeObserveIteractor @Inject constructor(
    private val repository: SomeRepository
) {

    fun observe(): Flow<List<SomeData>> = repository.observeSome()
}