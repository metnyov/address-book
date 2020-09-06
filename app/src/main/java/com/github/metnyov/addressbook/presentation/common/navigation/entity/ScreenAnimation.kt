package com.github.metnyov.addressbook.presentation.common.navigation.entity

import androidx.annotation.AnimRes

data class ScreenAnimation(
    @AnimRes val enter: Int,
    @AnimRes val exit: Int,
    @AnimRes val popEnter: Int,
    @AnimRes val popExit: Int
)