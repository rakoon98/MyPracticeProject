package com.example.test.data.repository

import com.example.test.data.network.ApiState
import com.example.test.data.network.SwitchGamesApiInterface
import kotlinx.coroutines.flow.Flow

interface TestRepository {

    suspend fun test(data : String) : Flow<ApiState<String>>
    suspend fun getSwitchGames() : Flow<ApiState<List<SwitchGamesApiInterface.Companion.SwitchGameData>>>

}