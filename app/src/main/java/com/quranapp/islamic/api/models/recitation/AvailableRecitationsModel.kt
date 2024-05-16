package com.quranapp.islamic.api.models.recitation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AvailableRecitationsModel(
    @SerialName("url-info") val urlInfo: RecitationsCommonUrlInfoModel,
    @SerialName("reciters") val reciters: List<RecitationInfoModel>
)
