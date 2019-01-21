package io.jeffchang.splitdiff.ui.gitdiff.widget

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import com.github.stkent.githubdiffparser.models.Hunk
import com.github.stkent.githubdiffparser.models.Line
import timber.log.Timber

/**
 * View that shows the before and after states of a diff.
 */
class DiffView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : FrameLayout(context, attrs, defStyleAttr) {

    private val beforeTextView = DiffTextView(context)

    private val afterTextView = TextView(context)

        var hunk: Hunk? = null
        set(value) {
            // Start with before state
            Timber.d("sdfasdf")

            val diff1 = ArrayList<String>()
            val diff2 = ArrayList<String>()

            value?.lines?.forEach {
                when (it.lineType!!) {
                    Line.LineType.FROM -> {
                        diff1 += it.content
                    }
                    Line.LineType.NEUTRAL -> {
                        diff1 += it.content
                        diff2 += it.content
                    }
                    Line.LineType.TO -> {
                        diff2 += it.content
                    }
                }
            }
            Timber.d("sdfasdf")
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