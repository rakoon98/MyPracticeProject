package com.example.test.data.network

import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.GET

interface SwitchGamesApiInterface {

    companion object {
        const val SWITCH_GAMES_URL = "switch/games"

        //        {
//            "id": 1,
//            "name": "#Breakforcist Battle",
//            "genre": [
//            "Party"
//            ],
//            "developers": [
//            "Lucid Sheep Games"
//            ],
//            "publishers": [
//            "Lucid Sheep Games"
//            ],
//            "releaseDates": {
//            "Japan": "Unreleased",
//            "NorthAmerica": "April 12, 2018",
//            "Europe": "April 12, 2018",
//            "Australia": "April 12, 2018"
//        }
//        },
        data class SwitchGameData(
            @SerializedName("id") val id: Int = 0,
            @SerializedName("name") val name: String = "",
            @SerializedName("genre") val genre: List<String> = emptyList(),
            @SerializedName("developers") val developers: List<String> = emptyList(),
            @SerializedName("publishers") val publishers: List<String> = emptyList(),
            @SerializedName("releaseDates") val releaseDates: SwitchGameDataReleaseData = SwitchGameDataReleaseData(),
        ) : java.io.Serializable

        data class SwitchGameDataReleaseData(
            @SerializedName("Japan") val japan: String = "",
            @SerializedName("NorthAmerica") val northAmerica: String = "",
            @SerializedName("Europe") val europe: String = "",
            @SerializedName("Australia") val Australia: String = "",
        )
    }

    @GET(SWITCH_GAMES_URL)
    suspend fun getSwitchGames(): Response<List<SwitchGameData>>

}