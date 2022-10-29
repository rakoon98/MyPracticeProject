package com.example.test.data.network

import com.example.test.data.model.swit.SwitchGameDataKtor
import com.example.test.data.network.SwitchGameApiKtor.Companion.KTOR_SWITCH_GAMES_URL
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SwitchGameApiKtorImpl @Inject constructor(
    private val httpClient : HttpClient
) : SwitchGameApiKtor {



    override suspend fun getSwitchGames(): Flow<ApiState<List<SwitchGameDataKtor>>> = flow {
        emit(ApiState.Loading())
        try {
            val response = httpClient.get<List<SwitchGameDataKtor>>(KTOR_SWITCH_GAMES_URL)
            emit(ApiState.Success(response))
        } catch (e:Exception) {
            emit(ApiState.Error(exception = e))
        }
    }
}