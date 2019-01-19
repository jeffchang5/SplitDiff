package io.jeffchang.splitdiff.common.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import io.jeffchang.splitdiff.R

/**
 * View that shows progress for loading screens.
 */
class LoadingView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : FrameLayout(context, attrs, defStyleAttr) {

    init {
        inflate(context, R.layout.view_loading_view, this)
    }

}