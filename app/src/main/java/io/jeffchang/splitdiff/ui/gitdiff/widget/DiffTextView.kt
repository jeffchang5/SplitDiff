package io.jeffchang.splitdiff.ui.gitdiff.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView

/**
 */
class DiffTextView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : TextView(context, attrs, defStyleAttr) {

    enum class DiffState {
        SAME,
        REMOVE,
        ADD
    }
}