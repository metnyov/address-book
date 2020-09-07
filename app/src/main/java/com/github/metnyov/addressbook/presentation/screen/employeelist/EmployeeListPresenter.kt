package com.github.metnyov.addressbook.presentation.screen.employeelist

import com.github.metnyov.addressbook.domain.entity.Employee
import com.github.metnyov.addressbook.domain.interactor.GetEmployeesUseCase
import com.github.metnyov.addressbook.domain.interactor.LoadDirectoryUseCase
import com.github.metnyov.addressbook.presentation.common.base.BasePresenter
import com.github.metnyov.addressbook.presentation.common.navigation.toScreen
import com.github.metnyov.addressbook.presentation.screen.employeelist.card.EmployeeCardFragment
import kotlinx.coroutines.launch
import moxy.InjectViewState
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.navigateToWithAnimation

@InjectViewState
class EmployeeListPresenter(
    override val router: Router,
    private val args: EmployeeListFragment.Args,
    private val loadDirectoryUseCase: LoadDirectoryUseCase,
    private val getEmployeesUseCase: GetEmployeesUseCase
) : BasePresenter<EmployeeListView>() {

    private var employees: List<Employee> = emptyList()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        launch {
            withLoading {
                loadEmployees()
            }
        }

        viewState.showTitle(args.title)
    }

    fun onRefresh() {
        launch {
            withLoading(withBackground = false) {
                loadDirectoryUseCase()
                loadEmployees()
            }
        }
    }

    fun onEmployeePressed(employee: Employee) {
        router.navigateToWithAnimation(
            EmployeeCardFragment.Args(employee, args.title).toScreen()
        )
    }

    private suspend fun loadEmployees() {
        employees = getEmployeesUseCase(args.departmentId)
        viewState.showEmployeeList(employees)
    }
}