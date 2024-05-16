package com.quranapp.islamic.frags.onboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.quranapp.islamic.adapters.transl.ADPTransls
import com.quranapp.islamic.components.transls.TranslBaseModel
import com.quranapp.islamic.frags.settings.FragSettingsTransl.LoadTranslsTask
import com.quranapp.islamic.utils.reader.TranslUtils
import com.quranapp.islamic.utils.sharedPrefs.SPReader
import com.quranapp.islamic.utils.thread.runner.CallableTaskRunner
import com.quranapp.islamic.utils.univ.FileUtils

class FragOnboardTranslations : FragOnboardBase() {
    private val translTaskRunner = CallableTaskRunner<List<TranslBaseModel>>()
    private var translSlugs: Set<String> = HashSet()

    override fun onDestroy() {
        translTaskRunner.cancel()
        super.onDestroy()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return RecyclerView(inflater.context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        translSlugs = SPReader.getSavedTranslations(view.context)
        initTranslations(view as RecyclerView)
    }

    private fun initTranslations(list: RecyclerView) {
        list.overScrollMode = View.OVER_SCROLL_IF_CONTENT_SCROLLS
        list.layoutManager = LinearLayoutManager(list.context)
        showTranslations(list)
    }

    private fun showTranslations(list: RecyclerView) {
        translTaskRunner.callAsync(
            object : LoadTranslsTask(FileUtils.newInstance(list.context), translSlugs) {
                override fun onComplete(translItems: List<TranslBaseModel>) {
                    if (translItems.isNotEmpty()) {
                        populateTranslations(list, translItems)
                    }
                }
            }
        )
    }

    private fun populateTranslations(list: RecyclerView, translItems: List<TranslBaseModel>) {
        list.adapter = ADPTransls(list.context, translItems) { ctx, model, isSelected ->
            TranslUtils.resolveSelectionChange(ctx, translSlugs, model, isSelected, true)
        }
    }
}
