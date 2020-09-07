package com.github.metnyov.addressbook.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.metnyov.addressbook.data.dao.DepartmentDao
import com.github.metnyov.addressbook.data.dao.EmployeeDao
import com.github.metnyov.addressbook.domain.entity.Department
import com.github.metnyov.addressbook.domain.entity.Employee

@Database(
    entities = [
        Department::class,
        Employee::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun departmentDao(): DepartmentDao

    abstract fun employeeDao(): EmployeeDao
}