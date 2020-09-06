package com.github.metnyov.addressbook.presentation.common.navigation.entity

import com.github.metnyov.addressbook.R

enum class TransitionType(val animation: ScreenAnimation) {
    DEFAULT(
        ScreenAnimation(
            enter = R.anim.fade_scale_enter,
            exit = R.anim.fade_scale_exit,
            popEnter = R.anim.fade_scale_enter,
            popExit = R.anim.fade_scale_exit
        )
    )
}