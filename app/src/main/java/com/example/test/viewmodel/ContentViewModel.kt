package com.example.test.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.compose.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContentViewModel @Inject constructor(

) : ViewModel(){

    private val _selectedIndex : MutableStateFlow<Int> = MutableStateFlow(-1)
    val selectedIndex : StateFlow<Int> get() = _selectedIndex.asStateFlow()

    private val _alarmListFlow : MutableStateFlow<List<Any>> = MutableStateFlow(emptyList())
    val alarmListFlow : StateFlow<List<Any>> get() = _alarmListFlow.asStateFlow()

    fun selectIndex(index:Int) = viewModelScope.launch {
        _selectedIndex.emit(index)
    }

    fun getAlarmListInViewModel() = viewModelScope.launch {
        getAlarmList()
            .catch { e ->
                Log.d("데이터체크","catch : ${e.message}")
            }.onEach {
                Log.d("데이터체크","onEach")
            }.onCompletion { result ->
                Log.d("데이터체크","onCompletion : ${result}")
            }.stateIn(this, SharingStarted.Eagerly, false)
    }

    suspend fun getAlarmList(): Flow<UiState<List<Any>>> = flow {
        emit(UiState.Loading)

        emit(
            UiState.Success(
                listOf(1,2,3,4,5,6,7)
            )
        )
    }

}