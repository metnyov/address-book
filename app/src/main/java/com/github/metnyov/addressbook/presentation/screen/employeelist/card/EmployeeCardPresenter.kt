package com.github.metnyov.addressbook.presentation.screen.employeelist.card

import com.github.metnyov.addressbook.domain.interactor.GetPhotoByEmployeeIdUseCase
import com.github.metnyov.addressbook.presentation.common.base.BasePresenter
import kotlinx.coroutines.launch
import moxy.InjectViewState
import ru.terrakok.cicerone.Router

@InjectViewState
class EmployeeCardPresenter(
    override val router: Router,
    private val args: EmployeeCardFragment.Args,
    private val getPhotoByEmployeeIdUseCase: GetPhotoByEmployeeIdUseCase
) : BasePresenter<EmployeeCardView>() {

    private val employee = args.employee

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        launch {
            withLoading {
                val photoUrl = getPhotoByEmployeeIdUseCase(employee.id)
                viewState.showPhotoUrl(photoUrl)
            }
        }

        viewState.showTitle(args.title)
        viewState.showEmployee(employee)
    }

    fun onPhonePressed() {
        viewState.startDialIntent(employee.phone)
    }

    fun onEmailPressed() {
        viewState.startEmailIntent(employee.email)
    }
}