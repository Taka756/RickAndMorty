package com.rommansabbir.rickmortyapp.feature.charactersview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.rommansabbir.rickmortyapp.base.uistate.BaseComposeUIState
import com.rommansabbir.rickmortyapp.data.remote.models.RickMortyCharactersListAPIResponse

class CharactersViewUIState : BaseComposeUIState() {
    var dataList: MutableList<RickMortyCharactersListAPIResponse.Companion.Result> =
        mutableStateListOf()
    var nextPaginatedURL: String? by mutableStateOf(null)
}