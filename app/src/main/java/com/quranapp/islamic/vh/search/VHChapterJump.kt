package com.quranapp.islamic.vh.search

import com.quranapp.islamic.components.search.ChapterJumpModel
import com.quranapp.islamic.components.search.SearchResultModelBase
import com.quranapp.islamic.utils.reader.factory.ReaderFactory.startChapter
import com.quranapp.islamic.widgets.chapterCard.ChapterCard

class VHChapterJump(private val chapterCard: ChapterCard, applyMargins: Boolean) : VHSearchResultBase(chapterCard) {
    init {
        setupJumperView(chapterCard, applyMargins)
    }

    override fun bind(model: SearchResultModelBase, pos: Int) {
        if (model is ChapterJumpModel) {
            chapterCard.apply {
                chapterNumber = model.chapterNo
                setName(model.name, model.nameTranslation)
                setOnClickListener { startChapter(it.context, model.chapterNo) }
            }
        }
    }
}