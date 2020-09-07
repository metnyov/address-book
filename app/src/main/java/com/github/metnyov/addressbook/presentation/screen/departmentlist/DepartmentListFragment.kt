package com.github.metnyov.addressbook.presentation.screen.departmentlist

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
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
            direct.instance(),
            direct.instance()
        )
    }

    private val departmentAdapter by lazy {
        DepartmentListAdapter(presenter::onDepartmentPressed)
    }

    private var lastDialog: AlertDialog? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarDepartmentList.run {
            setNavigationOnClickListener { presenter.onBackPressed() }
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.menuExit -> {
                        presenter.onExitPressed()
                        false
                    }
                    else -> true
                }
            }
        }
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

    override fun setBackButtonVisible(visible: Boolean) {
        toolbarDepartmentList.run {
            if (visible) {
                setNavigationIcon(R.drawable.ic_arrow_back)
            } else {
                navigationIcon = null
            }
        }
    }

    override fun showDepartmentList(departments: List<Department>) {
        departmentAdapter.submitList(departments)
    }

    override fun showConfirmExitDialog() {
        lastDialog?.dismiss()
        lastDialog = context?.let {
            AlertDialog.Builder(it)
                .setMessage(R.string.department_list_exit_dialog_message)
                .setPositiveButton(R.string.yes) { _, _ -> presenter.onExitConfirmed() }
                .setNegativeButton(R.string.cancel) { _, _ -> presenter.onExitDialogCancelPressed() }
                .show()
        }
    }

    override fun hideConfirmExitDialog() {
        lastDialog?.dismiss()
        lastDialog = null
    }

    @Parcelize
    data class Args(
        val parentId: String? = null,
        val title: String? = null
    ) : FragmentArgs<DepartmentListFragment>()
}