package com.github.metnyov.addressbook.presentation.screen.departmentlist

import com.github.metnyov.addressbook.domain.entity.Department
import com.github.metnyov.addressbook.domain.interactor.GetDepartmentsUseCase
import com.github.metnyov.addressbook.domain.interactor.LoadDirectoryUseCase
import com.github.metnyov.addressbook.presentation.common.base.BasePresenter
import com.github.metnyov.addressbook.presentation.common.navigation.toScreen
import com.github.metnyov.addressbook.presentation.screen.employeelist.EmployeeListFragment
import kotlinx.coroutines.launch
import moxy.InjectViewState
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.navigateToWithAnimation

@InjectViewState
class DepartmentListPresenter(
    override val router: Router,
    private val args: DepartmentListFragment.Args,
    private val loadDirectoryUseCase: LoadDirectoryUseCase,
    private val getDepartmentsUseCase: GetDepartmentsUseCase
) : BasePresenter<DepartmentListView>() {

    private var departments: List<Department> = emptyList()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        args.title?.let(viewState::showTitle)

        launch {
            withLoading {
                val isRootDepartments = args.parentId == null
                if (isRootDepartments) {
                    loadDirectoryUseCase()
                }
                loadDepartments()
            }
        }
    }

    fun onRefresh() {
        launch {
            withLoading(withBackground = false) {
                loadDirectoryUseCase()
                loadDepartments()
            }
        }
    }

    fun onDepartmentPressed(department: Department) {
        val nextScreen = if (department.isParent) {
            DepartmentListFragment.Args(department.id, department.name).toScreen()
        } else {
            EmployeeListFragment.Args(department.id, department.name).toScreen()
        }
        router.navigateToWithAnimation(nextScreen)
    }

    private suspend fun loadDepartments() {
        departments = getDepartmentsUseCase(args.parentId)
        viewState.showDepartmentList(departments)
    }
}