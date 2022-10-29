package com.example.test.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.data.model.swit.SwitchGameDataKtor
import com.example.test.data.network.ApiState
import com.example.test.data.network.SwitchGameApiKtorImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KtorViewModel @Inject constructor(
    private val switchGameApiKtorImpl : SwitchGameApiKtorImpl
) : ViewModel() {

    private val switchGamesStateFlow : MutableStateFlow<ApiState<List<SwitchGameDataKtor>>>
        = MutableStateFlow(ApiState.Loading())
    val getSwitchGamesStateFlow : StateFlow<ApiState<List<SwitchGameDataKtor>>> get() = switchGamesStateFlow.asStateFlow()

    fun getSwitchGames() = viewModelScope.launch {
        switchGameApiKtorImpl.getSwitchGames()
            .onEach {
                switchGamesStateFlow.emit(it)
            }
            .onCompletion { /***/ }
            .stateIn(viewModelScope, SharingStarted.Eagerly, false)
    }

}