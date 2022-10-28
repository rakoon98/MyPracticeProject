package com.example.test

import com.example.test.network.SwitchGamesApiInterface
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

interface TestRepository {

    suspend fun test(data : String) : Flow<ApiState<String>>
    suspend fun getSwitchGames() : Flow<ApiState<List<SwitchGamesApiInterface.Companion.SwitchGameData>>>

}