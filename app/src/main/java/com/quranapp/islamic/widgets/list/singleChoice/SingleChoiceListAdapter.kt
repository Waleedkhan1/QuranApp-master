package com.quranapp.islamic.widgets.list.singleChoice

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.quranapp.islamic.widgets.list.base.BaseListAdapter
import com.quranapp.islamic.widgets.list.base.BaseListItem
import com.quranapp.islamic.widgets.radio.PeaceRadioButton
import com.quranapp.islamic.widgets.radio.PeaceRadioGroup

class SingleChoiceListAdapter(context: Context) : BaseListAdapter(context) {
    override fun onCreateItemView(item: BaseListItem, position: Int): View {
        val radio = PeaceRadioButton(context).apply {
            tag = item
            setTexts(item.label, item.message)
        }

        if (item.id != View.NO_ID) {
            radio.id = item.id
        }

        return radio
    }

    override fun onAppendItemView(container: ViewGroup, itemView: View, position: Int) {
        super.onAppendItemView(container, itemView, position)

        if (getItem(position).selected && container is PeaceRadioGroup) {
            container.check(itemView.id)
        }
    }
}
