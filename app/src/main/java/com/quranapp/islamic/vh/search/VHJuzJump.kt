package com.quranapp.islamic.vh.search

import com.quranapp.islamic.components.search.JuzJumpModel
import com.quranapp.islamic.components.search.SearchResultModelBase
import com.quranapp.islamic.databinding.LytReaderJuzSpinnerItemBinding
import com.quranapp.islamic.utils.reader.factory.ReaderFactory.startJuz

class VHJuzJump(private val mBinding: LytReaderJuzSpinnerItemBinding, applyMargins: Boolean) : VHSearchResultBase(
    mBinding.root
) {
    init {
        setupJumperView(mBinding.root, applyMargins)
    }

    override fun bind(model: SearchResultModelBase, pos: Int) {
        (model as JuzJumpModel).apply {
            mBinding.juzSerial.text = model.juzSerial
            mBinding.root.setOnClickListener { startJuz(it.context, model.juzNo) }
        }
    }
}
