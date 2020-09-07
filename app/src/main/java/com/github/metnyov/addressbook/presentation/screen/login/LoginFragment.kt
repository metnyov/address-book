package com.github.metnyov.addressbook.presentation.screen.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.github.metnyov.addressbook.R
import com.github.metnyov.addressbook.presentation.common.base.BaseFragment
import com.github.metnyov.addressbook.presentation.common.navigation.parentRouter
import kotlinx.android.synthetic.main.fragment_login.*
import moxy.ktx.moxyPresenter
import org.kodein.di.direct
import org.kodein.di.instance

class LoginFragment : BaseFragment<LoginPresenter>(R.layout.fragment_login), LoginView {

    override val presenter: LoginPresenter by moxyPresenter {
        LoginPresenter(
            parentRouter,
            direct.instance()
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnLoginCheckout.setOnClickListener {
            presenter.onCheckoutPressed(
                username = etLoginUsername.text?.toString() ?: "",
                password = etLoginPassword.text?.toString() ?: ""
            )
        }
    }

    override fun showServerError(message: String?) =
        showToast(message ?: getString(R.string.login_error_generic))

    override fun showEmptyFieldsError() = showToast(getString(R.string.login_error_empty_fields))

    private fun showToast(message: String) {
        context?.let {
            Toast.makeText(it, message, Toast.LENGTH_SHORT).show()
        }
    }
}