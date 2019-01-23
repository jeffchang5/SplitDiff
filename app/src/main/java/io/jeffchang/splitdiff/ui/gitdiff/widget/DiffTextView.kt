package io.jeffchang.splitdiff.ui.gitdiff.widget

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import io.jeffchang.githubdiffparser.models.Range
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
        setPadding(dp16, dp16, dp16, 0)

        typeface = Typeface.MONOSPACE
    }

    fun setDiffLines(diffType: DiffType, contentList: List<Content>, range: Range) {
        val muteGrey = ContextCompat.getColor(context, R.color.muteGrey)
        val span = span {
            // Goes through the list and create different spans, some with color, some without.
            contentList.forEach { content ->
                if (content.type == Content.Type.SAME) {
                    backgroundColor(muteGrey) {
                        +(getColoredLine(diffType.diffSymbol, content.text))
                    }
                    return@forEach
                }
                diffType.color?.let {
                    val color = ContextCompat.getColor(context, it)
                    backgroundColor(color) {
                        +(getColoredLine(diffType.diffSymbol, content.text))
                    }
                }
            }
            // Creates extra lines.
            backgroundColor(muteGrey) {
                createExtraLines(range, contentList)?.let {
                    +it
                }
            }
        }
        text = span.build()
    }

    // This takes all the parameters and creates correct colored lines in the text view.
    private fun getColoredLine(diffSymbol: String, content: String): String {

        // Pads all the lines with blank characters so it appears the entire line is colored.
        val blanks = " ".repeat(findNumberOfCharsInLine() - content.length)

        return (diffSymbol + content + blanks).appendNewLineIfNeeded
    }

    // Creates extra lines.
    private fun createExtraLines(range: Range, contentList: List<Content>): String? {
        val extraLineCount = range.lineCount - contentList.size
        // If the diff is larger than the current line size, there shouldn't be any lines drawn
        if (extraLineCount < 0) return null
        val emptyLines = " ".repeat(findNumberOfCharsInLine()) + "\n"
        return emptyLines.repeat(extraLineCount)
    }

    private fun findNumberOfCharsInLine(): Int {
        // Finds how many single characters can find in the line.
        val wordWidth = paint.measureText("a",0,1)
        val screenWidth = context.resources.displayMetrics.widthPixels
        return (screenWidth / wordWidth).toInt()

    }

    enum class DiffType(@ColorRes val color: Int?, val diffSymbol: String) {
        BEFORE(R.color.red, "-"),
        SAME(R.color.muteGrey, " "),
        AFTER(R.color.green, "+")
    }

}