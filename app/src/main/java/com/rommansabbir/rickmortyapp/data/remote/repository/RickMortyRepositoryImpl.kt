package com.rommansabbir.rickmortyapp.data.remote.repository

import com.rommansabbir.rickmortyapp.base.api.client.executeAPIRequestV2
import com.rommansabbir.rickmortyapp.base.appresult.AppResult
import com.rommansabbir.rickmortyapp.data.remote.api.RickMortyAPIService
import com.rommansabbir.rickmortyapp.data.remote.models.RickMortyCharactersListAPIRequest
import com.rommansabbir.rickmortyapp.data.remote.models.RickMortyCharactersListAPIResponse
import com.rommansabbir.rickmortyapp.data.remote.models.RickMortySingleCharacterAPIRequest
import com.rommansabbir.rickmortyapp.data.remote.models.RickMortySingleCharacterDetailsAPIResponseModel
import javax.inject.Inject

class RickMortyRepositoryImpl @Inject constructor(private val apiService: RickMortyAPIService) :
    RickMortyRepository {

    override fun getAllCharacters(request: RickMortyCharactersListAPIRequest): AppResult<RickMortyCharactersListAPIResponse> {/*
        Check if @{paginatedURL} is null or not. If null call default api else call the @{paginatedURL}.
         */
        val apiToCall =
            if (request.paginatedURL == null) apiService.getCharactersListDefault() else apiService.getCharactersListPaginated(
                request.paginatedURL
            )
        return executeAPIRequestV2(
            apiToCall, { it }, RickMortyCharactersListAPIResponse()
        )
    }

    override fun getSingleCharacterDetails(request: RickMortySingleCharacterAPIRequest): AppResult<RickMortySingleCharacterDetailsAPIResponseModel> =
        executeAPIRequestV2(
            apiService.getCharacterDetail(request.id),
            { it },
            RickMortySingleCharacterDetailsAPIResponseModel()
        )
}