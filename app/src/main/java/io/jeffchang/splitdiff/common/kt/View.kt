package io.jeffchang.splitdiff.common.kt

import android.util.TypedValue
import android.view.View

fun View.dpToPixel(dip: Int) = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dip.toFloat(),
        resources.displayMetrics
).toInt()
