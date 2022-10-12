package com.example.presentation_lifecycle.ui.ui_some

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.presentation_lifecycle.domain.SomeObserveIteractor
import com.example.presentation_lifecycle.domain.SomeUpdateIteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SomeViewModel @Inject constructor(
    private val updateIteractor: SomeUpdateIteractor,
    observeIteractor: SomeObserveIteractor
) : ViewModel() {

    private val _loadingFlow = MutableStateFlow(true)

    var uiState: StateFlow<SomeUIState> = combine(
        observeIteractor.observe(),
        _loadingFlow
    ) { data, loading ->
        SomeUIState(
            users = data,
            userMessages = mutableListOf<String>().apply {
                data.forEach {
                    if (it.age > 25) {
                        add(it.message)
                    }
                }
            }.toList(),
            loading = loading
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        SomeUIState()
    )

    init {
        fetchSomeData()
    }

    fun fetchSomeData() {
        viewModelScope.launch(Dispatchers.IO) {
            _loadingFlow.emit(true)
            updateIteractor.fetch().collect()
            _loadingFlow.emit(false)
        }
    }
}