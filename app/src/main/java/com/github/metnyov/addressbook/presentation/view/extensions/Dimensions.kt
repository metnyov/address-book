@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.github.metnyov.addressbook.presentation.view.extensions

import android.content.Context
import android.view.View
import androidx.annotation.DimenRes

// Returns dp dimension value in pixels
fun Context.dp(value: Int): Int = (value * resources.displayMetrics.density).toInt()
fun Context.dp(value: Float): Int = (value * resources.displayMetrics.density).toInt()

// Return sp dimension value in pixels
fun Context.sp(value: Int): Int = (value * resources.displayMetrics.scaledDensity).toInt()
fun Context.sp(value: Float): Int = (value * resources.displayMetrics.scaledDensity).toInt()

fun Context.dimen(@DimenRes resource: Int): Int = resources.getDimensionPixelSize(resource)

// The same for the views
inline fun View.dp(value: Int): Int = context.dp(value)
inline fun View.dp(value: Float): Int = context.dp(value)
inline fun View.sp(value: Int): Int = context.sp(value)
inline fun View.sp(value: Float): Int = context.sp(value)
inline fun View.dimen(@DimenRes resource: Int): Int = context.dimen(resource)