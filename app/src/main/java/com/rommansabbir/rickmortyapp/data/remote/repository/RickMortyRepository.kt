package com.rommansabbir.rickmortyapp.data.remote.repository

import com.rommansabbir.rickmortyapp.base.appresult.AppResult
import com.rommansabbir.rickmortyapp.data.remote.models.RickMortyCharactersListAPIRequest
import com.rommansabbir.rickmortyapp.data.remote.models.RickMortyCharactersListAPIResponse
import com.rommansabbir.rickmortyapp.data.remote.models.RickMortySingleCharacterAPIRequest
import com.rommansabbir.rickmortyapp.data.remote.models.RickMortySingleCharacterDetailsAPIResponseModel

interface RickMortyRepository {
    fun getAllCharacters(request: RickMortyCharactersListAPIRequest): AppResult<RickMortyCharactersListAPIResponse>

    fun getSingleCharacterDetails(request: RickMortySingleCharacterAPIRequest): AppResult<RickMortySingleCharacterDetailsAPIResponseModel>
}