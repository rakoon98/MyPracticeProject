package com.example.test.util

import com.example.test.data.network.ApiState
import retrofit2.HttpException
import retrofit2.Response

fun <T> getResponseConvert( res : Response<T> ) : ApiState<T> {
//    CoroutineExceptionHandler { _, throwable -> ApiState.Error(exception = throwable as Exception) }
    return try {
        when ( res.code() ) {
            in 200..299 -> {
                ApiState.Success<T>(data = res.body()!!)
            }
            else -> {
                throw HttpException(res)
            }
        }
    } catch ( e : Exception ) {
//        this.errorBody()
        ApiState.Error(exception = e)
    }
}