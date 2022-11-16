package com.example.stackoverflowapp.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stackoverflowapp.data.Item
import com.example.stackoverflowapp.data.StackOverflowModel
import com.example.stackoverflowapp.repo.StackOverflowRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val stackOverflowRepo: StackOverflowRepo) : ViewModel() {


    private val _state = MutableStateFlow(emptyList<Item>())
    val state: StateFlow<List<Item>>
    get() = _state


    init {
        viewModelScope.launch {
            val stackOverflow = stackOverflowRepo.getQuestions()
            _state.value=stackOverflow.items
        }
    }
}
