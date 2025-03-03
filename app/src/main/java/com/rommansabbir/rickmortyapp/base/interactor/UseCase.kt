package com.rommansabbir.rickmortyapp.base.interactor

import com.rommansabbir.rickmortyapp.base.appresult.AppResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class UseCase<Type, in Params> where Type : Any {
    abstract suspend fun run(params: Params): AppResult<Type>
    suspend operator fun invoke(
        params: Params
    ): AppResult<Type> = withContext(Dispatchers.IO) { run(params) }

    class None
}