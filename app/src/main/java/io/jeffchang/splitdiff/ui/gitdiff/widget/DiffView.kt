package io.jeffchang.splitdiff.ui.gitdiff.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.view.GestureDetectorCompat
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import io.jeffchang.splitdiff.data.model.gitdiff.Hunk


/**
 * View that shows the before and after states of a diff.
 */

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
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

    private var hunk: Hunk? = null
        set(value) {
            value?.fromList?.let {
                beforeTextView.setDiffLines(DiffTextView.DiffType.BEFORE, it, value.range)
            }
            value?.toList?.let {
                afterTextView.setDiffLines(DiffTextView.DiffType.AFTER, it, value.range)
            }
            showDiffViewState(DiffTextView.DiffType.BEFORE)
        }

    // You can annotate your methods with @ModelProp
    @ModelProp
    fun hunk(hunk: Hunk) {
        this.hunk = hunk
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

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        val view = findViewAtPosition(
                this,
                event.x.toInt(),
                event.y.toInt()
        )

        // Handles the case that the finger leaves the boundaries of the DiffTextView and makes sure
        // the state goes back to the original state.
        if ((view !is DiffTextView) and
                (event.action == MotionEvent.ACTION_UP)) {
            showDiffViewState(DiffTextView.DiffType.BEFORE)
            longClicked = false
        }
        return super.dispatchTouchEvent(event)
    }


    // Recursively finds views in ViewGroups recursively.
    private fun findViewAtPosition(parent: View, x: Int, y: Int): View? {
        if (parent is ViewGroup) {
            for (i in 0 until parent.childCount) {
                val child = parent.getChildAt(i)
                return findViewAtPosition(child, x, y)
            }
            return null
        } else {
            val rect = Rect()
            parent.getGlobalVisibleRect(rect)
            return if (rect.contains(x, y)) {
                parent
            } else {
                null
            }
        }
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