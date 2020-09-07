package com.github.metnyov.addressbook.presentation.screen.login

import com.github.metnyov.addressbook.common.exceptions.LoginException
import com.github.metnyov.addressbook.domain.interactor.LoginUseCase
import com.github.metnyov.addressbook.presentation.common.base.BasePresenter
import com.github.metnyov.addressbook.presentation.common.navigation.toScreen
import com.github.metnyov.addressbook.presentation.screen.departmentlist.DepartmentListFragment
import kotlinx.coroutines.launch
import moxy.InjectViewState
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.replaceScreenWithAnimation

@InjectViewState
class LoginPresenter(
    override val router: Router,
    private val loginUseCase: LoginUseCase
) : BasePresenter<LoginView>() {

    override fun onError(t: Throwable) {
        when (t) {
            is LoginException -> viewState.showServerError(t.message)
            else -> super.onError(t)
        }
    }

    fun onCheckoutPressed(username: String, password: String) {
        if (username.isNotEmpty() && password.isNotEmpty()) {
            launch {
                withLoading(withBackground = false) {
                    loginUseCase(username, password)
                    router.replaceScreenWithAnimation(DepartmentListFragment.Args().toScreen())
                }
            }
        } else {
            viewState.showEmptyFieldsError()
        }
    }
}