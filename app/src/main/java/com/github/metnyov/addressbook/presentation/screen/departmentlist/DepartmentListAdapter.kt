package com.github.metnyov.addressbook.presentation.screen.departmentlist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.metnyov.addressbook.R
import com.github.metnyov.addressbook.domain.entity.Department
import com.github.metnyov.addressbook.presentation.common.EqualsDiffCallback
import kotlinx.android.extensions.LayoutContainer

class DepartmentListAdapter(
    private val itemClickListener: (Department) -> Unit
) : ListAdapter<Department, DepartmentListAdapter.ViewHolder>(
    EqualsDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_item_department, parent, false) as TextView
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        override val containerView: TextView
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(department: Department) {
            containerView.run {
                text = department.name
                setOnClickListener {
                    itemClickListener(department)
                }
            }
        }
    }
}