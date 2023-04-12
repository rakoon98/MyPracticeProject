package com.example.test.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.data.model.PersonModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SharedViewModel : ViewModel() {

    var person by mutableStateOf<PersonModel?>(null)

    private val _personFlow = MutableStateFlow<PersonModel?>(null)
    val personFlow : StateFlow<PersonModel?> get() = _personFlow.asStateFlow()

    fun changePerson(newPerson : PersonModel) = viewModelScope.launch {
        _personFlow.emit(newPerson)
    }
}