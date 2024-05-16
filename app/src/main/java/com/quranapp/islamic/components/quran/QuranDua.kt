package com.quranapp.islamic.components.quran

import android.content.Context
import com.quranapp.islamic.utils.quran.parser.QuranDuaParser
import java.util.concurrent.atomic.AtomicReference

object QuranDua {
    private val sQuranDuaRef = AtomicReference<List<ExclusiveVerse>>()
    fun prepareInstance(
        context: Context,
        quranMeta: QuranMeta,
        callback: (List<ExclusiveVerse>) -> Unit
    ) {
        if (sQuranDuaRef.get() == null) {
            prepare(context, quranMeta, callback)
        } else {
            callback(sQuranDuaRef.get())
        }
    }

    private fun prepare(
        context: Context,
        quranMeta: QuranMeta,
        callback: (List<ExclusiveVerse>) -> Unit
    ) {
        QuranDuaParser.parseDua(
            context,
            quranMeta,
            sQuranDuaRef
        ) { callback(sQuranDuaRef.get()) }
    }
}
