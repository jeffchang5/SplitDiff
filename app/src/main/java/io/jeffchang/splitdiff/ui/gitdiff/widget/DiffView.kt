package io.jeffchang.splitdiff.ui.gitdiff.widget

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.view.GestureDetectorCompat
import io.jeffchang.splitdiff.R
import io.jeffchang.splitdiff.data.model.gitdiff.Hunk

/**
 * View that shows the before and after states of a diff.
 */
class DiffView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : CardView(context, attrs, defStyleAttr) {

    private val beforeTextView = DiffTextView(context)

    private val afterTextView = DiffTextView(context)

    // Used to keep track if MotionEvent.UP action is from a long press.
    private var longClicked = false

    private val gestureDetector by lazy {
        val gestureDetector = object : GestureDetector.SimpleOnGestureListener() {

            override fun onDown(e: MotionEvent?): Boolean {
                longClicked = false
                return super.onDown(e)
            }

            override fun onLongPress(e: MotionEvent?) {
                super.onLongPress(e)
                longClicked = true
                showDiffViewState(DiffTextView.DiffType.AFTER)
            }
        }
        GestureDetectorCompat(context, gestureDetector)
    }

    var hunk: Hunk? = null
        set(value) {
            value?.fromList?.let {
                beforeTextView.setDiffLines(DiffTextView.DiffType.BEFORE, it, value.range)
            }
            value?.toList?.let {
                afterTextView.setDiffLines(DiffTextView.DiffType.AFTER, it, value.range)
            }
            showDiffViewState(DiffTextView.DiffType.BEFORE)
        }

    init {
        val layoutParams = LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        )
        addView(beforeTextView, layoutParams)
        addView(afterTextView, layoutParams)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        gestureDetector.onTouchEvent(event)
        if ((event.action == MotionEvent.ACTION_UP) and longClicked) {
            showDiffViewState(DiffTextView.DiffType.BEFORE)
            return true
        }
        return true
    }

    private fun showDiffViewState(diffType: DiffTextView.DiffType) {
        if (diffType == DiffTextView.DiffType.BEFORE) {
            beforeTextView.visibility = View.VISIBLE
            afterTextView.visibility = View.INVISIBLE
        } else if (diffType == DiffTextView.DiffType.AFTER) {
            beforeTextView.visibility = View.INVISIBLE
            afterTextView.visibility = View.VISIBLE
        }
    }

}