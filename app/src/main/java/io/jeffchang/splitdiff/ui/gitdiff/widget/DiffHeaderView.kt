package io.jeffchang.splitdiff.ui.gitdiff.widget

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import io.jeffchang.splitdiff.R
import kotlinx.android.synthetic.main.diff_header_view.view.*

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class DiffHeaderView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        inflate(context, R.layout.diff_header_view, this)
    }

    @ModelProp
    fun fromText(fromText: String) {
        diff_header_view_from_textview.text = fromText
    }

    @ModelProp
    fun toText(toText: String) {
        diff_header_view_to_textview.text = toText
    }

}