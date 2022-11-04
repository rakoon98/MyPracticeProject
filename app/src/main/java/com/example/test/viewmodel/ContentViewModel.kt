package com.example.test.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContentViewModel @Inject constructor(

) : ViewModel(){

    private val _selectedIndex : MutableStateFlow<Int> = MutableStateFlow(-1)
    val selectedIndex : StateFlow<Int> get() = _selectedIndex.asStateFlow()

    fun selectIndex(index:Int) = viewModelScope.launch {
        _selectedIndex.emit(index)
    }

}