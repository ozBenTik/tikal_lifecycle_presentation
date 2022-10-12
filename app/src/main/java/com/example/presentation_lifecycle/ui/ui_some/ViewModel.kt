package com.example.presentation_lifecycle.ui.ui_some

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.presentation_lifecycle.domain.SomeObserveIteractor
import com.example.presentation_lifecycle.domain.SomeUpdateIteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PresentationViewModel @Inject constructor(
    val updateIteractor: SomeUpdateIteractor,
    observeIteractor: SomeObserveIteractor
) : ViewModel() {

    private val loadingFlow = MutableStateFlow(true)

    var uiState: StateFlow<SomeUIState> = combine(
        observeIteractor.observe(),
        loadingFlow
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
            loading = loadingFlow.value
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        SomeUIState()
    )

    init {
        fetchData()
    }

    fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            loadingFlow.emit(true)
            updateIteractor.fetch().collect()
            loadingFlow.emit(false)
        }
    }
}