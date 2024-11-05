package com.lggpmlrg.canitaspadv2_a.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lggpmlrg.canitaspadv2_a.data.TareasRepository
import com.lggpmlrg.canitaspadv2_a.model.Tarea
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class TareasScreenViewModel(itemsRepository: TareasRepository): ViewModel() {
    private val _uiState = MutableStateFlow(AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()

    val busquedaInput = mutableStateOf("")

    val tareaUiState: StateFlow<TaskUiState> =
        itemsRepository.getAllItemsStream().map { TaskUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = TaskUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class TaskUiState(val itemList: List<Tarea> = listOf())