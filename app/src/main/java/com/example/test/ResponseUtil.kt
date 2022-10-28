package com.example.test

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import retrofit2.HttpException
import retrofit2.Response

fun <T> getResponseConvert( res : Response<T> ) : ApiState<T> {
//    CoroutineExceptionHandler { _, throwable -> ApiState.Error(exception = throwable as Exception) }
    return try {
        when ( res.code() ) {
            in 200..299 -> { ApiState.Success<T>(data = res.body()!!) }
            else -> {
                throw HttpException(res)
            }
        }
    } catch ( e : Exception ) {
//        this.errorBody()
        ApiState.Error(exception = e)
    }
}