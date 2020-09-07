package com.github.metnyov.addressbook.data.entity

import com.github.metnyov.addressbook.domain.entity.Department
import com.squareup.moshi.Json

data class NwDepartment(

    @Json(name = "ID")
    val id: String? = null,

    @Json(name = "Name")
    val name: String? = null,

    @Json(name = "Departments")
    val departments: List<NwDepartment?>? = null,

    @Json(name = "Employees")
    val employees: List<NwEmployee?>? = null
)

fun NwDepartment.toDepartment(parentId: String?, isParent: Boolean): Department? = run {
    Department(
        id = id ?: return@run null,
        name = name ?: return@run null,
        parentId = parentId,
        isParent = isParent
    )
}