package com.quranapp.islamic.widgets.list.singleChoice

import android.content.Context
import android.view.ViewGroup
import com.quranapp.islamic.widgets.list.base.BaseListItem
import com.quranapp.islamic.widgets.list.base.BaseListView
import com.quranapp.islamic.widgets.radio.PeaceRadioGroup

class SingleChoiceListView(context: Context) : BaseListView(context) {
    private var selectionChangeListener: OnItemClickListener? = null

    override fun createItemsContainer(context: Context): ViewGroup {
        val radioGroup = PeaceRadioGroup(context)
        radioGroup.onCheckChangedListener = { button, _ ->
            if (selectionChangeListener != null) {
                val item = button.tag as BaseListItem?
                if (item != null) {
                    selectionChangeListener!!.onItemClick(item)
                }
            }
        }
        return radioGroup
    }

    override fun setOnItemClickListener(listener: OnItemClickListener) {
        selectionChangeListener = listener
    }
}
