package com.rommansabbir.rickmortyapp.base.appresult

import com.rommansabbir.rickmortyapp.base.appresult.AppResult.Error
import com.rommansabbir.rickmortyapp.base.appresult.AppResult.Success
import com.rommansabbir.rickmortyapp.base.failure.Failure

/**
 * Sealed class that represent the API Results.
 *
 * [AppResult] has two state called [Success] and [Error].
 */
sealed class AppResult<T> {

    class Success<T>(var data: T?) : AppResult<T>()

    class Error<T>(var failure: Failure) : AppResult<T>()

    fun isError(): Boolean = this is Error<*>

    @Throws(Exception::class)
    fun asFailure(): Failure {
        return try {
            (this as Error<*>).failure
        } catch (e: Exception) {
            throw e
        }
    }

    @Throws(Exception::class)
    fun <T> asSuccess(): T {
        return try {
            (this as Success<T>).data!!
        } catch (e: Exception) {
            throw e
        }
    }

    @Throws(Exception::class)
    inline fun <T> parseResult(
        crossinline success: (T) -> Unit,
        crossinline error: (Failure) -> Unit
    ) {
        when (this.isError()) {
            true -> error.invoke(this.asFailure())
            else -> success.invoke(this.asSuccess())
        }
    }
}
