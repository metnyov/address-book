package com.github.metnyov.addressbook.presentation.common

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

class EqualsDiffCallback<TItem : Any>(
    private val isSameItems: (TItem, TItem) -> Boolean = { p0, p1 -> p0 == p1 }
) : DiffUtil.ItemCallback<TItem>() {

    override fun areItemsTheSame(p0: TItem, p1: TItem) = isSameItems(p0, p1)

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(p0: TItem, p1: TItem) = p0 == p1
}