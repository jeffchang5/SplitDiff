package io.jeffchang.splitdiff.ui.gitdiff.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import io.jeffchang.splitdiff.R
import io.jeffchang.splitdiff.data.model.gitdiff.Content
import io.jeffchang.splitdiff.ui.common.span

/**
 */
class DiffTextView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : TextView(context, attrs, defStyleAttr) {

    fun setDiffLines(diffType: DiffType, fromList: List<Content>) {
        val span = span {
            fromList.forEach {
                val colorRes = if (it.type == Content.Type.SAME) {
                    R.color.white
                } else {
                    diffType.color
                }
                val color = ContextCompat.getColor(context, colorRes)
                backgroundColor(color) {
                    +it.content
                }
            }
        }

        text = span.build()
    }

    enum class DiffType(@ColorRes val color: Int) {
        BEFORE(R.color.red),
        AFTER(R.color.green)
    }
}