package com.example.test.data.model.swit

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class SwitchGameDataKtor(
    val id: Int = 0,
    val name: String = "",
    val genre: List<String> = emptyList(),
    val developers: List<String> = emptyList(),
    val publishers: List<String> = emptyList(),
    val releaseDates: SwitchGameDataReleaseData = SwitchGameDataReleaseData(),
)

@Serializable
data class SwitchGameDataReleaseData(
    val Japan: String = "",
    val NorthAmerica: String = "",
    val Europe: String = "",
    val Australia: String = "",
)