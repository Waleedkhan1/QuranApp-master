package com.quranapp.islamic.views.homepage2

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import com.quranapp.islamic.R
import com.quranapp.islamic.activities.reference.ActivityEtiquette
import com.quranapp.islamic.adapters.reference.ADPEtiquette
import com.quranapp.islamic.components.quran.ExclusiveVerse
import com.quranapp.islamic.components.quran.QuranEtiquette
import com.quranapp.islamic.components.quran.QuranMeta

class QuranEtiquetteLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : HomepageCollectionLayoutBase(context, attrs, defStyleAttr) {
    override fun getHeaderTitle(): Int {
        return R.string.titleEtiquetteVerses
    }

    override fun getHeaderIcon(): Int {
        return R.drawable.veiled_muslim
    }

    override fun onViewAllClick() {
        context.startActivity(Intent(context, ActivityEtiquette::class.java))
    }

    private fun refreshVerses(ctx: Context, verses: List<ExclusiveVerse>) {
        hideLoader()

        val featured = verses.subList(0, verses.size.coerceAtMost(5))
        resolveListView().apply {
            layoutManager = LinearLayoutManager(ctx)
            adapter = ADPEtiquette(featured)
        }
    }

    override fun refresh(quranMeta: QuranMeta) {
        showLoader()

        QuranEtiquette.prepareInstance(context, quranMeta) { references ->
            refreshVerses(context, references)
        }
    }
}