package com.ravi.cookbook.utils

import com.ravi.cookbook.model.Area

interface APIStatus {
    fun onRetrieveSuccess(list: Any)
    fun onRetrieveStart()
    fun onRetrieveFinish()
    fun onRetrieveError(error: Throwable?)
}