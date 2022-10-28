package com.example.test

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.network.SwitchGamesApiInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FlowViewModel @Inject constructor(
    private val repository : TestRepositoryImpl
): ViewModel() {

    private val on : MutableStateFlow<Int> = MutableStateFlow(1)
    val one : StateFlow<Int> get() = on.asStateFlow()

    private val apiStateFlow : MutableStateFlow<ApiState<String>> = MutableStateFlow(ApiState.Loading())
    val getApiStateFlow : StateFlow<ApiState<String>> get() = apiStateFlow.asStateFlow()

    private val switchGamesStateFlow : MutableStateFlow<ApiState<List<SwitchGamesApiInterface.Companion.SwitchGameData>>> = MutableStateFlow(ApiState.Loading())
    val getSwitchGamesStateFlow : StateFlow<ApiState<List<SwitchGamesApiInterface.Companion.SwitchGameData>>> get() = switchGamesStateFlow.asStateFlow()


    fun requestApiState(data : String) = viewModelScope.launch {
        Log.d("데이터체크","requestApiState data => $data")
        repository.test(data)
            .onEach {
                apiStateFlow.emit(it)
                Log.d("데이터체크","onEach => $it")
            }
            .onCompletion {
                Log.d("데이터체크","onCompletion")
            }.stateIn(
                viewModelScope,
                SharingStarted.Eagerly,
//                SharingStarted.Lazily,
//                SharingStarted.WhileSubscribed(replayExpirationMillis = 10 * 1000L),
                false
            )
    }

    fun getSwitchGames() = viewModelScope.launch {
        repository.getSwitchGames()
            .onEach {
                Log.d("데이터체크","스위치게임즈 : ${it.data?.size} 개 가져옴")
                switchGamesStateFlow.emit(it) }
            .onCompletion {  }
            .stateIn(
                viewModelScope,
                SharingStarted.Eagerly,
//                SharingStarted.Lazily,
//                SharingStarted.WhileSubscribed(replayExpirationMillis = 10 * 1000L),
                false
            )
    }

}