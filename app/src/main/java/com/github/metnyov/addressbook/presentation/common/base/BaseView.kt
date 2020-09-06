package com.github.metnyov.addressbook.presentation.common.base

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleTagStrategy
import moxy.viewstate.strategy.StateStrategyType

interface BaseView : MvpView {

    @StateStrategyType(AddToEndSingleTagStrategy::class, tag = "loading")
    fun showLoading(withBackground: Boolean)

    @StateStrategyType(AddToEndSingleTagStrategy::class, tag = "loading")
    fun hideLoading()
}