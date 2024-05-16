package com.quranapp.islamic.utils.quran.parser

import android.content.Context
import android.os.Handler
import android.os.Looper
import com.quranapp.islamic.components.quran.QuranMeta
import com.quranapp.islamic.components.quran.ExclusiveVerse
import com.quranapp.islamic.utils.Log
import java.util.concurrent.atomic.AtomicReference

object QuranDuaParser : ExclusiveVersesParser() {
    fun parseDua(
        context: Context,
        quranMeta: QuranMeta,
        quranDuaRef: AtomicReference<List<ExclusiveVerse>>,
        postRunnable: Runnable
    ) {
        Thread {
            try {
                quranDuaRef.set(
                    parseFromAssets(
                        context,
                        quranMeta,
                        "type1"
                    )
                )
            } catch (e: Exception) {
                Log.saveError(e, "QuranDuaParser.parseDua")
            }

            Handler(Looper.getMainLooper()).post(postRunnable)
        }.start()
    }
}
