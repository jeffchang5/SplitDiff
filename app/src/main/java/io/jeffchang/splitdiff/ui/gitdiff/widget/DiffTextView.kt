package io.jeffchang.splitdiff.ui.gitdiff.widget

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import io.jeffchang.splitdiff.R
import io.jeffchang.splitdiff.common.kt.appendNewLineIfNeeded
import io.jeffchang.splitdiff.common.kt.dpToPixel
import io.jeffchang.splitdiff.data.model.gitdiff.Content
import io.jeffchang.splitdiff.ui.common.span

/**
 */
class DiffTextView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : TextView(context, attrs, defStyleAttr) {

    init {
        textSize = 20f

        val dp16 = dpToPixel(16)
        setPadding(dp16, dp16, dp16, dp16)

        typeface = Typeface.MONOSPACE
    }

    fun setDiffLines(diffType: DiffType, fromList: List<Content>) {
        val span = span {
            fromList.forEach { content ->
                if (content.type == Content.Type.SAME) {
                    +content.text.appendNewLineIfNeeded
                    return@forEach
                }
                diffType.color?.let {
                    val color = ContextCompat.getColor(context, it)
                    backgroundColor(color) {
                        +(diffType.diffSymbol + content.text.appendNewLineIfNeeded)
                    }
                }
            }
        }
        text = span.build()
    }

    enum class DiffType(@ColorRes val color: Int?, val diffSymbol: String) {
        BEFORE(R.color.red, "-"),
        SAME(null, " "),
        AFTER(R.color.green, "+")
    }
}