package io.jeffchang.splitdiff.ui.gitdiff.widget

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.FrameLayout
import io.jeffchang.splitdiff.common.kt.plusAssign
import io.jeffchang.splitdiff.data.model.gitdiff.Hunk
import java.lang.StringBuilder

/**
 * View that shows the before and after states of a diff.
 */
class DiffView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : FrameLayout(context, attrs, defStyleAttr) {

    private val beforeTextView = DiffTextView(context)

    private val afterTextView = DiffTextView(context)

    var hunk: Hunk? = null
        set(value) {
            value?.fromList?.let {
                setBeforeLines(it)
            }

        }

    private fun setBeforeLines(fromList: List<String>) {
        val stringBuilder = StringBuilder()
        stringBuilder += fromList
        beforeTextView.text = stringBuilder.toString()
    }

    private fun setAfterLines(afterList: List<String>) {

    }


    init {
        val layoutParams = LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        )
        addView(beforeTextView, layoutParams)
        addView(afterTextView, layoutParams)
    }

}