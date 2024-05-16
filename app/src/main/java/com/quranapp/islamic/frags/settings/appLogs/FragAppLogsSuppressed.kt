package com.quranapp.islamic.frags.settings.appLogs

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.quranapp.islamic.R
import com.quranapp.islamic.adapters.appLogs.ADPAppLogs
import com.quranapp.islamic.components.appLogs.AppLogModel
import com.quranapp.islamic.databinding.FragSettingsTranslBinding
import com.quranapp.islamic.frags.BaseFragment
import com.quranapp.islamic.utils.Log
import com.quranapp.islamic.utils.extended.GapedItemDecoration
import com.quranapp.islamic.utils.extensions.dp2px
import com.quranapp.islamic.utils.extensions.updatePaddingHorizontal
import com.quranapp.islamic.utils.univ.DateUtils
import com.quranapp.islamic.utils.univ.FileUtils
import com.quranapp.islamic.widgets.PageAlert

class FragAppLogsSuppressed : BaseFragment() {
    private lateinit var binding: FragSettingsTranslBinding
    private lateinit var fileUtils: FileUtils

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frag_settings_transl, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fileUtils = FileUtils.newInstance(view.context)
        binding = FragSettingsTranslBinding.bind(view)

        init(view.context)
    }

    private fun init(context: Context) {
        val logs = ArrayList<AppLogModel>()

        val files = Log.SUPPRESSED_ERROR_DIR.listFiles()
        if (files.isNullOrEmpty()) {
            PageAlert(context).apply {
                setIcon(null)
                setMessage("", context.getString(R.string.textNoLogsFound))
                show(binding.container)
            }
            binding.loader.visibility = View.GONE
            return
        }

        files.sortedByDescending { it.lastModified() }.forEach { logFile ->
            val (datetimeStr, place) = logFile.nameWithoutExtension.split("@")
            val log = logFile.readText()
            val logShort = if (log.length > 200) log.substring(0, 200) + "... ${log.length - 200} more chars" else log
            val parsedDate = DateUtils.toDate(datetimeStr, Log.FILE_NAME_DATE_FORMAT)
            val formattedDateTime = if (parsedDate != null) DateUtils.format(parsedDate, DateUtils.DATETIME_FORMAT_USER) else datetimeStr

            logs.add(
                    AppLogModel(
                            formattedDateTime,
                            place,
                            logFile,
                            log,
                            logShort,
                    )
            )
        }

        binding.list.let {
            it.addItemDecoration(GapedItemDecoration(context.dp2px(10F)))
            it.updatePaddingHorizontal(context.dp2px(15F))
            it.layoutManager = LinearLayoutManager(context)
            it.adapter = ADPAppLogs(logs, Color.parseColor("#A58A2A"))
        }
        binding.loader.visibility = View.GONE
    }
}