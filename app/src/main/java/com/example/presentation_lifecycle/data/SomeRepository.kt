package com.example.presentation_lifecycle.data

import com.example.presentation_lifecycle.model.SomeData
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SomeRepository @Inject constructor(
    private val localDataSource: SomeLocalDataSource,
    private val remoteDataSource: SomeRemoteDataSource,
) {

    fun getSomeData() =
        flow {
            emit(remoteDataSource.getData())
        }

    fun saveSomeData(list: List<SomeData>) {
        localDataSource.someLocalStore.insert(list)
    }

    fun observeSome() = localDataSource.someLocalStore.observeEntries()

}