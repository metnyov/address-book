package com.github.metnyov.addressbook.presentation.screen.departmentlist

import com.github.metnyov.addressbook.domain.entity.Department
import com.github.metnyov.addressbook.presentation.common.base.BaseView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface DepartmentListView : BaseView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showTitle(title: String)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showDepartmentList(departments: List<Department>)
}