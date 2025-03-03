package com.rommansabbir.rickmortyapp.base.api.client

import com.rommansabbir.rickmortyapp.base.appresult.AppResult
import com.rommansabbir.rickmortyapp.base.failure.Failure
import retrofit2.Call
import java.net.UnknownHostException

fun <T, R> executeAPIRequestV2(
    call: Call<T>,
    transform: (T) -> R,
    default: T,
    postRequest: (R) -> Unit = {},
): AppResult<R> {
    return try {
        val response = call.execute()
        return when (response.isSuccessful) {
            true -> {
                val transformed = transform((response.body() ?: default))
                postRequest(transformed)
                AppResult.Success(transformed)
            }

            false -> AppResult.Error(
                getFailureTypeAccordingToHTTPCode(
                    response.code()
                )
            )
        }
    } catch (exception: Throwable) {
        when (exception) {
            is UnknownHostException -> {
                AppResult.Error(Failure.HTTP.NetworkConnection)
            }
            else -> {AppResult.Error(Failure.ActualException(exception))}
        }

    }
}

internal fun getFailureTypeAccordingToHTTPCode(httpCode: Int): Failure {
    return when (httpCode) {
        400 -> return Failure.HTTP.BadRequest
        401 -> return Failure.HTTP.UnauthorizedError
        403 -> return Failure.HTTP.Forbidden
        404 -> return Failure.HTTP.NotFound
        405 -> return Failure.HTTP.MethodNotAllowed
        429 -> return Failure.HTTP.TooManyRequest
        in 402..409 -> return Failure.HTTP.CanNotConnectToTheServer
        in 500..599 -> return Failure.HTTP.InternalServerError
        else -> Failure.HTTP.CanNotConnectToTheServer
    }
}
