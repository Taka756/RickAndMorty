package com.rommansabbir.rickmortyapp.base.failure.manager

import com.rommansabbir.rickmortyapp.base.failure.Failure

interface FailureManager {
    fun handleFailure(failure: Failure): String
}