package com.rommansabbir.rickmortyapp.data.local.models

import com.rommansabbir.rickmortyapp.data.remote.models.RickMortyCharactersListAPIResponse
import com.rommansabbir.storex.StoreAbleObject

data class CacheCharactersListRequestModel(
    val paginatedURL: String?,
    val list: MutableList<RickMortyCharactersListAPIResponse.Companion.Result>
) : StoreAbleObject()