package com.quranapp.islamic.components.search

class VerseJumpModel(
    val chapterNo: Int,
    val fromVerseNo: Int,
    val toVerseNo: Int,
    val titleText: String,
    val chapterNameText: String
) : SearchResultModelBase()
