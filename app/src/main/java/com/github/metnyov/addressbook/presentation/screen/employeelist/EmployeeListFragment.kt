package com.github.metnyov.addressbook.presentation.screen.employeelist

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.github.metnyov.addressbook.R
import com.github.metnyov.addressbook.domain.entity.Employee
import com.github.metnyov.addressbook.presentation.common.args.FragmentArgs
import com.github.metnyov.addressbook.presentation.common.base.BaseFragmentWithArgs
import com.github.metnyov.addressbook.presentation.common.navigation.parentRouter
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.fragment_employee_list.*
import moxy.ktx.moxyPresenter
import org.kodein.di.direct
import org.kodein.di.instance

class EmployeeListFragment :
    BaseFragmentWithArgs<EmployeeListPresenter, EmployeeListFragment.Args>(R.layout.fragment_employee_list),
    EmployeeListView {

    override val presenter: EmployeeListPresenter by moxyPresenter {
        EmployeeListPresenter(
            parentRouter,
            args,
            direct.instance(),
            direct.instance()
        )
    }

    private val employeeListAdapter by lazy {
        EmployeeListAdapter(presenter::onEmployeePressed)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarEmployeeList.setNavigationOnClickListener { presenter.onBackPressed() }
        srlEmployeeList.setOnRefreshListener { presenter.onRefresh() }
        rvEmployeeList.adapter = employeeListAdapter
    }

    override fun hideLoading() {
        super.hideLoading()
        srlEmployeeList.isRefreshing = false
    }

    override fun showTitle(title: String) {
        toolbarEmployeeList.title = title
    }

    override fun showEmployeeList(employees: List<Employee>) {
        employeeListAdapter.submitList(employees)
        rvEmployeeList.isVisible = employees.isNotEmpty()
        tvEmployeeListEmpty.isVisible = employees.isEmpty()
    }

    @Parcelize
    data class Args(
        val departmentId: String,
        val title: String
    ) : FragmentArgs<EmployeeListFragment>()
}