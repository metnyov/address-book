package com.github.metnyov.addressbook.presentation.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.github.metnyov.addressbook.R
import com.github.metnyov.addressbook.presentation.view.extensions.dp

class LoadingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_loading, this)
        isClickable = true
        isFocusable = true
        elevation = dp(4).toFloat()
    }

    fun show(animate: Boolean = true) {
        if (visibility != View.VISIBLE) {
            if (animate) {
                animate()
                    .setDuration(DEFAULT_PROGRESS_ANIM_DURATION)
                    .alpha(1f)
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationStart(animation: Animator?) {
                            visibility = View.VISIBLE
                        }
                    })
                    .start()
            } else {
                visibility = View.VISIBLE
            }
        }
    }

    fun hide(animate: Boolean = true) {
        if (visibility != View.GONE) {
            if (animate) {
                animate()
                    .setDuration(DEFAULT_PROGRESS_ANIM_DURATION)
                    .alpha(0f)
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationCancel(animation: Animator?) {
                            visibility = View.GONE
                        }

                        override fun onAnimationEnd(animation: Animator?) {
                            visibility = View.GONE
                        }
                    })
                    .start()
            } else {
                visibility = View.GONE
            }
        }
    }

    companion object {
        private const val DEFAULT_PROGRESS_ANIM_DURATION = 300L
    }
}