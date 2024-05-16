package com.quranapp.islamic.adapters.extended

import android.content.Context
import android.view.View
import com.quranapp.islamic.R
import com.quranapp.islamic.utils.extensions.color
import com.quranapp.islamic.utils.extensions.dp2px
import com.quranapp.islamic.utils.extensions.updatePaddingVertical
import com.quranapp.islamic.widgets.list.base.BaseListAdapter
import com.quranapp.islamic.widgets.list.base.BaseListItem
import com.quranapp.islamic.widgets.list.base.BaseListItemView

class PeaceBottomSheetMenuAdapter(context: Context) : BaseListAdapter(context) {
    private val mMessageColor = context.color(R.color.colorText2)

    override fun onCreateItemView(item: BaseListItem, position: Int): View {
        val view = super.onCreateItemView(item, position) as BaseListItemView

        if (item.message.isNullOrEmpty()) {
            view.containerView.updatePaddingVertical(context.dp2px(3f))
        } else {
            view.messageView?.setTextColor(mMessageColor)
        }

        return view
    }
}
