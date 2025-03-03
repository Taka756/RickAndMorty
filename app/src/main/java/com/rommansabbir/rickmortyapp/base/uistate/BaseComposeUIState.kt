package com.rommansabbir.rickmortyapp.base.uistate

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue


open class BaseComposeUIState {
    var isLoading by mutableStateOf(false)
    var loadData by mutableStateOf(false)
    var showRootView by mutableStateOf(false)
}