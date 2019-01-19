package io.jeffchang.splitdiff.common.widget

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.widget.FrameLayout
import io.jeffchang.splitdiff.R
import io.jeffchang.splitdiff.data.model.TextData
import kotlinx.android.synthetic.main.view_loading_view.view.*

/**
 * View that shows progress for loading screens.
 */
class LoadingView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : FrameLayout(context, attrs, defStyleAttr) {

    var textData: TextData? = null
        set(value) {
            value?.text?.let {
                view_loading_textview.setText(value.text)

            }
            startAnimation(value)
        }

    init {
        inflate(context, R.layout.view_loading_view, this)
    }

    // Using animate API to make visibility changes smoother.
    private fun startAnimation(textData: TextData?) {
        val animateAlpha = if (textData == null) {
            0f
        } else {
            1f
        }

        // This view will be View.GONE if any data will loaded. It must be visible for the animation
        // to appear.
        visibility = View.VISIBLE

        animate()
                .alpha(animateAlpha)
                .setDuration(750)
                .setInterpolator(AccelerateInterpolator())
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        super.onAnimationEnd(animation)
                        visibility = if (textData != null) {
                            View.VISIBLE
                        } else {
                            View.GONE
                        }
                    }
                })
    }

}