package com.rommansabbir.rickmortyapp.data.local.repository

import com.rommansabbir.rickmortyapp.base.appresult.AppResult
import com.rommansabbir.rickmortyapp.data.local.models.CacheCharactersListRequestModel
import com.rommansabbir.rickmortyapp.data.remote.models.RickMortyCharactersListAPIResponse

interface LocalCache {
    fun cacheCharactersListLocally(requestModel: CacheCharactersListRequestModel): AppResult<Boolean>

    fun getCharactersListFromLocal(): AppResult<RickMortyCharactersListAPIResponse>
}