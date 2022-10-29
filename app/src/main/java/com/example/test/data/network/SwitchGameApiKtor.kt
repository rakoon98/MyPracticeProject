package com.example.test.data.network

import com.example.test.data.model.swit.SwitchGameDataKtor
import kotlinx.coroutines.flow.Flow

interface SwitchGameApiKtor {

    companion object {
        val base = "https://api.sampleapis.com/"

        val KTOR_SWITCH_GAMES_URL = "${base}switch/games"
    }

    suspend fun getSwitchGames() : Flow<ApiState<List<SwitchGameDataKtor>>>

}