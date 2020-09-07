package com.github.metnyov.addressbook.presentation.screen.login

import com.github.metnyov.addressbook.presentation.common.base.BaseView
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

interface LoginView : BaseView {

    @StateStrategyType(SkipStrategy::class)
    fun showServerError(message: String?)

    @StateStrategyType(SkipStrategy::class)
    fun showEmptyFieldsError()
}