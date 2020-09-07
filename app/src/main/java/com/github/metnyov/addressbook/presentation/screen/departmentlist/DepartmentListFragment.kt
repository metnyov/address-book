package com.github.metnyov.addressbook.presentation.screen.departmentlist

import android.os.Bundle
import android.view.View
import com.github.metnyov.addressbook.R
import com.github.metnyov.addressbook.domain.entity.Department
import com.github.metnyov.addressbook.presentation.common.args.FragmentArgs
import com.github.metnyov.addressbook.presentation.common.base.BaseFragmentWithArgs
import com.github.metnyov.addressbook.presentation.common.navigation.parentRouter
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.fragment_department_list.*
import moxy.ktx.moxyPresenter
import org.kodein.di.direct
import org.kodein.di.instance

class DepartmentListFragment :
    BaseFragmentWithArgs<DepartmentListPresenter, DepartmentListFragment.Args>(R.layout.fragment_department_list),
    DepartmentListView {

    override val presenter: DepartmentListPresenter by moxyPresenter {
        DepartmentListPresenter(
            parentRouter,
            args,
            direct.instance(),
            direct.instance()
        )
    }

    private val departmentAdapter by lazy {
        DepartmentListAdapter(presenter::onDepartmentPressed)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarDepartmentList.setNavigationOnClickListener { presenter.onBackPressed() }
        srlDepartmentList.setOnRefreshListener { presenter.onRefresh() }
        rvDepartmentList.adapter = departmentAdapter
    }

    override fun hideLoading() {
        super.hideLoading()
        srlDepartmentList.isRefreshing = false
    }

    override fun showTitle(title: String) {
        toolbarDepartmentList.title = title
    }

    override fun showDepartmentList(departments: List<Department>) {
        departmentAdapter.submitList(departments)
    }

    @Parcelize
    data class Args(
        val parentId: String? = null,
        val title: String? = null
    ) : FragmentArgs<DepartmentListFragment>()
}