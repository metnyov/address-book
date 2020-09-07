package com.github.metnyov.addressbook.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.metnyov.addressbook.domain.entity.Employee

@Dao
interface EmployeeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg employees: Employee)

    @Query("SELECT * FROM Employee WHERE departmentId IS :departmentId")
    suspend fun getByDepartmentId(departmentId: String): List<Employee>
}