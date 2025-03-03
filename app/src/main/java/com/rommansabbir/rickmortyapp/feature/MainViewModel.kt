package com.rommansabbir.rickmortyapp.feature

import androidx.lifecycle.ViewModel
import com.rommansabbir.rickmortyapp.base.appresult.AppResult
import com.rommansabbir.rickmortyapp.base.interactor.UseCase
import com.rommansabbir.rickmortyapp.data.local.models.CacheCharactersListRequestModel
import com.rommansabbir.rickmortyapp.data.remote.models.RickMortyCharactersListAPIRequest
import com.rommansabbir.rickmortyapp.data.remote.models.RickMortyCharactersListAPIResponse
import com.rommansabbir.rickmortyapp.data.remote.models.RickMortySingleCharacterAPIRequest
import com.rommansabbir.rickmortyapp.data.remote.models.RickMortySingleCharacterDetailsAPIResponseModel
import com.rommansabbir.rickmortyapp.domain.CacheCharactersListToLocalUseCase
import com.rommansabbir.rickmortyapp.domain.GetCharactersListFromLocalUseCase
import com.rommansabbir.rickmortyapp.domain.GetRickMortyCharacterDetailUseCase
import com.rommansabbir.rickmortyapp.domain.GetRickMortyCharacterListUseCase
import com.rommansabbir.rickmortyapp.feature.characterdetailview.CharactersDetailsViewUIState
import com.rommansabbir.rickmortyapp.feature.charactersview.CharactersViewUIState
import com.rommansabbir.rickmortyapp.utils.extensions.nullString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val characterListUseCase: GetRickMortyCharacterListUseCase,
    private val cacheCharactersListToLocalUseCase: CacheCharactersListToLocalUseCase,
    private val getCharactersListFromLocalUseCase: GetCharactersListFromLocalUseCase,
    private val getCharacterDetailUseCase: GetRickMortyCharacterDetailUseCase
) : ViewModel() {

    var isFirstRun: Boolean = false


    val characterListUIState by lazy { CharactersViewUIState() }

    val charactersDetailsViewUIState by lazy { CharactersDetailsViewUIState() }

    val uiState by lazy { MainUIState() }

    suspend fun getCharacterListFromRemote(request: RickMortyCharactersListAPIRequest): AppResult<RickMortyCharactersListAPIResponse> =
        characterListUseCase(request)

    suspend fun getCharacterDetailRemote(request: RickMortySingleCharacterAPIRequest): AppResult<RickMortySingleCharacterDetailsAPIResponseModel> =
        getCharacterDetailUseCase(request)

    suspend fun getCharactersListFromLocal(): AppResult<RickMortyCharactersListAPIResponse> =
        getCharactersListFromLocalUseCase(
            UseCase.None()
        )

    suspend fun cacheCharactersListToLocal(request: CacheCharactersListRequestModel) =
        cacheCharactersListToLocalUseCase(request)

    suspend fun mapAPIResponseToUIState(result: AppResult<RickMortyCharactersListAPIResponse>) {
        withContext(Dispatchers.Default) {
            val apiResponse = result.asSuccess<RickMortyCharactersListAPIResponse>()
            characterListUIState.nextPaginatedURL = apiResponse.paginationInfo.next
            val temp = characterListUIState.dataList
            temp.addAll(apiResponse.results)
            characterListUIState.dataList = temp
        }
    }

    suspend fun mapAPIResponseToDetailUIState(result: AppResult<RickMortySingleCharacterDetailsAPIResponseModel>) {
        withContext(Dispatchers.Default) {
            val apiResponse = result.asSuccess<RickMortySingleCharacterDetailsAPIResponseModel>()
            charactersDetailsViewUIState.title = apiResponse.name ?: nullString()
            charactersDetailsViewUIState.image = apiResponse.image ?: ""
            val infoList = mutableListOf<Pair<String, String>>().apply {
                add(Pair("Status:", apiResponse.status ?: nullString()))
                add(Pair("Gender:", apiResponse.gender ?: nullString()))
                add(Pair("Species:", apiResponse.species ?: nullString()))
                add(Pair("Origin:", apiResponse.origin?.name ?: nullString()))
                add(Pair("Location:", apiResponse.location?.name ?: nullString()))
            }
            charactersDetailsViewUIState.informationList.clear()
            charactersDetailsViewUIState.informationList.addAll(infoList)
            charactersDetailsViewUIState.totalEpisodes = apiResponse.episode.size
        }
    }
}