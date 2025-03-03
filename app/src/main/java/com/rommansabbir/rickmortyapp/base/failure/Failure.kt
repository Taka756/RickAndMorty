package com.rommansabbir.rickmortyapp.base.failure


sealed class Failure {
    data class ActualException(val exception: Throwable) : Failure()

    object HTTP {
        object Forbidden : Failure()
        object NotFound : Failure()
        object MethodNotAllowed : Failure()
        object NetworkConnection : Failure()
        object UnauthorizedError : Failure()
        object BadRequest : Failure()
        object CanNotConnectToTheServer : Failure()
        object TooManyRequest : Failure()
        object InternalServerError : Failure()
    }

    object LocalCache {
        object NotExistInCache : Failure()
        data class FailedToCache(val message: String) : Failure()
    }
}