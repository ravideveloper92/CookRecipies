package com.ravi.cookbook.utils

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import kotlin.math.ceil

class ResizableImageView : AppCompatImageView {

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context) : super(context)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val d = drawable

        if (d != null) {
            // ceil not round - avoid thin vertical gaps along the left/right
            // edges
            val width = MeasureSpec.getSize(widthMeasureSpec)
            val height =
                ceil((width.toFloat() * d.intrinsicHeight.toFloat() / d.intrinsicWidth.toFloat()).toDouble())
                    .toInt()
            // int height=10;
            setMeasuredDimension(width, height)
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
    }
}