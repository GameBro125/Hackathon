package com.example.hackathon

import android.content.res.Resources
import android.util.TypedValue
import android.view.View
import kotlin.math.roundToInt


/**
 * Convert number as "dp" to device pixels
 */
fun Number.dpToPx() = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    this.toFloat(),
    Resources.getSystem().displayMetrics
).roundToInt()
