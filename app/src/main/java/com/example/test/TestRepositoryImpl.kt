package com.example.test

import android.util.Log
import com.example.test.network.SwitchGamesApiInterface
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TestRepositoryImpl @Inject constructor(
    private val switchGamesApiInterface : SwitchGamesApiInterface
): TestRepository {
    override suspend fun test(data : String) = flow<ApiState<String>> {
        emit(ApiState.Loading())

        delay(300)
        emit(ApiState.Success("Over The Test Call => $data"))
    }


    override suspend fun getSwitchGames(): Flow<ApiState<List<SwitchGamesApiInterface.Companion.SwitchGameData>>> = flow {
        emit(ApiState.Loading())
        emit(getResponseConvert(switchGamesApiInterface.getSwitchGames()))
    }
}