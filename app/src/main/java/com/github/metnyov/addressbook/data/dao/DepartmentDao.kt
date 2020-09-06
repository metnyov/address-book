package com.github.metnyov.addressbook.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.metnyov.addressbook.domain.entity.Department

@Dao
interface DepartmentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg departments: Department)

    @Query("SELECT * FROM Department WHERE parentId == :parentId")
    suspend fun getByParentId(parentId: String?): List<Department>
}