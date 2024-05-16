package com.quranapp.islamic.widgets.compound

import androidx.annotation.IdRes
import com.quranapp.islamic.widgets.radio.PeaceRadioButton

interface PeaceCompoundButtonGroup {
    fun clearCheck()

    /**
     * Check a button without invoking the Listeners.
     *
     * @param id Id of the [PeaceRadioButton] to be checked.
     */
    fun checkSansInvocation(@IdRes id: Int)
    fun checkAtPosition(position: Int)
    fun check(@IdRes id: Int)
}
