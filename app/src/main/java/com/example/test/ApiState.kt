package com.example.test

/**
 *  공용 상태관리 및 api response 데이터
 */
sealed class ApiState<T> constructor(
    val data : T? = null,
    val exception : Exception? = null
){
    class Success<T>(data : T) : ApiState<T>(data)
    class Error<T>(exception: Exception, data : T? = null) : ApiState<T>(data = data, exception = exception)
    class Loading<T> : ApiState<T>()
}