package com.example.presentation_lifecycle.data

import com.example.presentation_lifecycle.model.SomeData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SomeLocalStore  @Inject constructor() {
    private val _someData = MutableSharedFlow<List<SomeData>>(replay = 1)

    fun insert(data: List<SomeData>) {
        _someData.tryEmit(data)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun clear() {
        _someData.resetReplayCache()
    }

    fun observeEntries() = _someData.asSharedFlow()
}