/*
 * Created by Faisal Khan on (c) 16/8/2021.
 */
package com.quranapp.islamic.components.storageCleanup

import com.quranapp.islamic.components.quran.subcomponents.QuranTranslBookInfo
import com.quranapp.islamic.components.transls.TranslBaseModel

class TranslationCleanupItemModel(val bookInfo: QuranTranslBookInfo) : TranslBaseModel() {
    var isDeleted: Boolean = false
}
