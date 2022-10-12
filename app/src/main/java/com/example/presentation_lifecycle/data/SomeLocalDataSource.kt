package com.example.presentation_lifecycle.data

import com.example.presentation_lifecycle.data.SomeLocalStore
import javax.inject.Inject

class SomeLocalDataSource @Inject constructor(
    val someLocalStore: SomeLocalStore,
)