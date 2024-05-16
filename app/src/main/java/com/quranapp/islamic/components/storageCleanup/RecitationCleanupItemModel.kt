/*
 * Created by Faisal Khan on (c) 16/8/2021.
 */
package com.quranapp.islamic.components.storageCleanup

import com.quranapp.islamic.api.models.recitation.RecitationInfoModel

data class RecitationCleanupItemModel(
    val recitationModel: RecitationInfoModel,
    val downloadsCount: Int,
    var isCleared: Boolean = false
)
