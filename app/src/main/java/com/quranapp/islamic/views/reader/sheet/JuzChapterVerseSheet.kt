package com.quranapp.islamic.views.reader.sheet

import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.quranapp.islamic.widgets.bottomSheet.PeaceBottomSheet

class JuzChapterVerseSheet : PeaceBottomSheet() {
    override fun onCreate(savedInstanceState: Bundle?) {
        params.apply {
            initialBehaviorState = BottomSheetBehavior.STATE_EXPANDED
            disableDragging = true
            fullHeight = false
            windowDimAmount = .9F
        }

        super.onCreate(savedInstanceState)
    }
}
