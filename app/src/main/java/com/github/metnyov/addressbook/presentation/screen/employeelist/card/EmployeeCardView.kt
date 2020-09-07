package com.github.metnyov.addressbook.presentation.screen.employeelist.card

import com.github.metnyov.addressbook.domain.entity.Employee
import com.github.metnyov.addressbook.presentation.common.base.BaseView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

interface EmployeeCardView : BaseView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showTitle(title: String)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showEmployee(employee: Employee)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showPhotoUrl(url: String)

    @StateStrategyType(SkipStrategy::class)
    fun startDialIntent(phone: String)

    @StateStrategyType(SkipStrategy::class)
    fun startEmailIntent(email: String)
}