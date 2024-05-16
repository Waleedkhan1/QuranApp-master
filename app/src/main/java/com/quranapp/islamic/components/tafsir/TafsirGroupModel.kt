/*
 * Created by Faisal Khan on (c) 16/8/2021.
 */
package com.quranapp.islamic.components.tafsir

import com.quranapp.islamic.api.models.tafsir.TafsirInfoModel

class TafsirGroupModel(
    val langCode: String,
) {
    var langName = ""
    var tafsirs: List<TafsirInfoModel> = ArrayList()
    var isExpanded = false
}
