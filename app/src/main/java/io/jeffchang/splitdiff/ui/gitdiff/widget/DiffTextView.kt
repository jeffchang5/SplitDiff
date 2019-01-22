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
 * Takes lines from a [Content] model and displays them on the screen in this view.
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

    fun setDiffLines(diffType: DiffType, contentList: List<Content>) {
        val span = span {
            // Goes through the list and create different spans, some with color, some without.
            contentList.forEach { content ->
                if (content.type == Content.Type.SAME) {
                    +content.text.appendNewLineIfNeeded
                    return@forEach
                }
                diffType.color?.let {
                    val color = ContextCompat.getColor(context, it)
                    backgroundColor(color) {
                        +(getColoredLine(diffType.diffSymbol, content.text))
                    }
                }
            }
        }
        text = span.build()
    }

    // This takes all the parameters and creates correct colored lines in the text view.
    private fun getColoredLine(diffSymbol: String, content: String): String {
        // Finds how many single characters can find in the line.
        val wordWidth = paint.measureText("a",0,1)
        val screenWidth = context.resources.displayMetrics.widthPixels
        val numberOfChars = (screenWidth / wordWidth).toInt()

        // Pads all the lines with blank characters so it appears the entire line is colored.
        val blanks = " ".repeat(numberOfChars - content.length)

        return (diffSymbol + content + blanks).appendNewLineIfNeeded
    }

    enum class DiffType(@ColorRes val color: Int?, val diffSymbol: String) {
        BEFORE(R.color.red, "-"),
        SAME(null, " "),
        AFTER(R.color.green, "+")
    }

}