package com.quranapp.islamic.activities.reference

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.quranapp.islamic.R
import com.quranapp.islamic.adapters.reference.ADPSolutionVerses
import com.quranapp.islamic.components.quran.ExclusiveVerse
import com.quranapp.islamic.components.quran.QuranMeta
import com.quranapp.islamic.components.quran.SituationVerse
import com.quranapp.islamic.databinding.ActivityExclusiveVersesBinding

class ActivitySolutionVerses : ActivityExclusiveVersesBase() {
    override fun onQuranMetaReady(
        activityView: View,
        intent: Intent,
        savedInstanceState: Bundle?,
        quranMeta: QuranMeta
    ) {
        SituationVerse.prepareInstance(this, quranMeta) { references ->
            initContent(ActivityExclusiveVersesBinding.bind(activityView), references, R.string.titleSolutionVerses)
        }
    }

    override fun getAdapter(
        context: Context,
        width: Int,
        exclusiveVerses: List<ExclusiveVerse>
    ): RecyclerView.Adapter<*> {
        return ADPSolutionVerses(context, width, exclusiveVerses)
    }
}