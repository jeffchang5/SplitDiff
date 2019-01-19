package io.jeffchang.splitdiff.ui.common

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import io.jeffchang.splitdiff.R


/**
 * A decoration that draws a line on RecyclerViews.
 */

class LineItemDecoration(context: Context?) : RecyclerView.ItemDecoration() {

    private val divider: Drawable?
            = context?.let { ContextCompat.getDrawable(it, R.drawable.line_item_decoration) }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)

            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = child.bottom + params.bottomMargin
            val bottom = top + divider?.intrinsicHeight!!

            divider.setBounds(left, top, right, bottom)
            divider.draw(c)
        }
    }
}