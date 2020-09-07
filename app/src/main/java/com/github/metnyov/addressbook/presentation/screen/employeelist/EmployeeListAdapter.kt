package com.github.metnyov.addressbook.presentation.screen.employeelist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.metnyov.addressbook.R
import com.github.metnyov.addressbook.domain.entity.Employee
import com.github.metnyov.addressbook.presentation.common.EqualsDiffCallback
import kotlinx.android.extensions.LayoutContainer

class EmployeeListAdapter(
    private val itemClickListener: (Employee) -> Unit
) : ListAdapter<Employee, EmployeeListAdapter.ViewHolder>(
    EqualsDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_item_employee, parent, false) as TextView
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        override val containerView: TextView
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(employee: Employee) {
            containerView.run {
                text = employee.name
                setOnClickListener {
                    itemClickListener(employee)
                }
            }
        }
    }
}