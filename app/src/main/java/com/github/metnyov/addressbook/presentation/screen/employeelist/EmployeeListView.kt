package com.github.metnyov.addressbook.presentation.screen.employeelist

import com.github.metnyov.addressbook.domain.entity.Employee
import com.github.metnyov.addressbook.presentation.common.base.BaseView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface EmployeeListView : BaseView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showTitle(title: String)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showEmployeeList(employees: List<Employee>)
}