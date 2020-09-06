package com.github.metnyov.addressbook.data.entity

import com.github.metnyov.addressbook.domain.entity.Employee
import com.squareup.moshi.Json

data class NwEmployee(

	@Json(name = "ID")
	val id: String? = null,

	@Json(name = "Name")
	val name: String? = null,

	@Json(name = "Title")
	val title: String? = null,

	@Json(name = "Email")
	val email: String? = null,

	@Json(name = "Phone")
	val phone: String? = null
)

fun NwEmployee.toEmployee(departmentId: String): Employee? = run {
    Employee(
		id = id ?: return@run null,
		name = name ?: return@run null,
		departmentId = departmentId,
		position = title ?: "",
		email = email ?: "",
		phone = phone ?: ""
	)
}