package com.github.metnyov.addressbook.presentation.screen.employeelist.card

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.net.toUri
import androidx.core.view.isVisible
import com.github.metnyov.addressbook.R
import com.github.metnyov.addressbook.domain.entity.Employee
import com.github.metnyov.addressbook.presentation.common.args.FragmentArgs
import com.github.metnyov.addressbook.presentation.common.base.BaseFragmentWithArgs
import com.github.metnyov.addressbook.presentation.common.navigation.parentRouter
import com.squareup.picasso.Picasso
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.fragment_employee_card.*
import moxy.ktx.moxyPresenter
import org.kodein.di.direct
import org.kodein.di.instance

class EmployeeCardFragment :
    BaseFragmentWithArgs<EmployeeCardPresenter, EmployeeCardFragment.Args>(R.layout.fragment_employee_card),
    EmployeeCardView {

    override val presenter: EmployeeCardPresenter by moxyPresenter {
        EmployeeCardPresenter(
            parentRouter,
            args,
            direct.instance()
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarEmployeeCard.setNavigationOnClickListener { presenter.onBackPressed() }
        tvEmployeeCardPhone.setOnClickListener { presenter.onPhonePressed() }
        tvEmployeeCardEmail.setOnClickListener { presenter.onEmailPressed() }
    }

    override fun showTitle(title: String) {
        toolbarEmployeeCard.title = title
    }

    override fun showEmployee(employee: Employee) = with(employee) {
        tvEmployeeCardName.text = name.removePrefix(" ").removeSuffix(" ").replace(" ", "\n")
        tvEmployeeCardPosition.setTextAndVisible(
            getString(R.string.employee_card_position, position)
        )
        tvEmployeeCardPhone.setTextAndVisible(phone)
        tvEmployeeCardEmail.setTextAndVisible(email)
    }

    private fun TextView.setTextAndVisible(text: String) {
        isVisible = text.isNotEmpty()
        this.text = text
    }

    override fun showPhotoUrl(url: String) {
        Picasso.get()
            .load(url)
            .placeholder(ColorDrawable(Color.LTGRAY))
            .into(ivEmployeeCardPhoto)
    }

    override fun startDialIntent(phone: String) =
        startActivity(Intent(Intent.ACTION_DIAL, "tel:$phone".toUri()))

    override fun startEmailIntent(email: String) =
        startActivity(Intent(Intent.ACTION_SENDTO, "mailto:$email".toUri()))

    @Parcelize
    data class Args(
        val employee: Employee,
        val title: String
    ) : FragmentArgs<EmployeeCardFragment>()
}