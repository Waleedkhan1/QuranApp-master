package com.quranapp.islamic.components

import com.quranapp.islamic.components.quran.subcomponents.Verse

class ReferenceVerseItemModel(
    val viewType: Int,
    val verse: Verse?,
    val chapterNo: Int,
    val fromVerse: Int,
    val toVerse: Int,
    val titleText: String?,
    var bookmarked: Boolean,
)
