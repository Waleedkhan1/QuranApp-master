package com.quranapp.islamic.frags.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.quranapp.islamic.R
import com.quranapp.islamic.activities.reference.ActivityQuranScience
import com.quranapp.islamic.components.quran.QuranMeta
import com.quranapp.islamic.databinding.FragMainBinding
import com.quranapp.islamic.frags.BaseFragment
import com.quranapp.islamic.interfaceUtils.OnResultReadyCallback
import com.quranapp.islamic.utils.app.UpdateManager
import com.quranapp.islamic.views.VOTDView

class FragMain : BaseFragment() {
    private lateinit var binding: FragMainBinding
    private lateinit var updateManager: UpdateManager
    private var votdView: VOTDView? = null

    override fun networkReceiverRegistrable(): Boolean {
        return true
    }

    override fun onPause() {
        super.onPause()
        updateManager.onPause()
    }


    override fun onResume() {
        super.onResume()
        updateManager.onResume()
        val context = context ?: return

        QuranMeta.prepareInstance(context, object : OnResultReadyCallback<QuranMeta> {
            override fun onReady(r: QuranMeta) {
                votdView?.post { votdView?.refresh(r) }
                binding.readHistory.post { binding.readHistory.refresh(r) }
            }
        })
    }

    override fun onDestroy() {
        votdView?.destroy()
        binding.readHistory.destroy()
        super.onDestroy()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragMainBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateManager = UpdateManager(view.context, binding.appUpdateContainer)

        // If update is not critical, proceed to load the rest of the content
        if (!updateManager.check4Update()) {
            QuranMeta.prepareInstance(view.context,
                object : OnResultReadyCallback<QuranMeta> {
                    override fun onReady(r: QuranMeta) {
                        initContent(r)
                    }
                }
            )
        }

    }

    private fun initContent(quranMeta: QuranMeta) {
        initVOTD(quranMeta)
        binding.let {
            arrayOf(
                it.readHistory,
                it.featuredReading,
                it.featuredDua,
                it.solutions,
                it.etiquette,
                it.prophets
            ).forEach { layout ->
                layout.initialize()
                layout.refresh(quranMeta)
            }

            it.quranScience.visibility = View.VISIBLE
            it.quranScience.clipToOutline = true
            it.quranScience.setOnClickListener {v->
                v.context.startActivity(Intent(v.context, ActivityQuranScience::class.java))
            }
        }
    }


    private fun initVOTD(quranMeta: QuranMeta) {
        votdView = VOTDView(binding.container.context).apply {
            id = R.id.homepageVOTD
            refresh(quranMeta)
            binding.container.addView(this, 1)
        }
    }

}