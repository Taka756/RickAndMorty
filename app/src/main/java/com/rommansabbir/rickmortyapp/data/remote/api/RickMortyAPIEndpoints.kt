package com.rommansabbir.rickmortyapp.data.remote.api

import com.rommansabbir.rickmortyapp.base.api.APIEndpoints
import com.rommansabbir.rickmortyapp.data.remote.models.RickMortyCharactersListAPIResponse
import com.rommansabbir.rickmortyapp.data.remote.models.RickMortySingleCharacterDetailsAPIResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url



interface RickMortyAPIEndpoints {
    @GET(APIEndpoints.Characters.CharacterEndpoint)
    fun getCharactersListDefault(): Call<RickMortyCharactersListAPIResponse>

    @GET
    fun getCharactersListPaginated(
        @Url url: String
    ): Call<RickMortyCharactersListAPIResponse>

    @GET("${APIEndpoints.Characters.CharacterEndpoint}/{id}")
    fun getCharacterDetail(
        @Path("id") id: Int
    ): Call<RickMortySingleCharacterDetailsAPIResponseModel>
}