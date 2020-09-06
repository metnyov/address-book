package com.github.metnyov.addressbook.di

import androidx.room.Room
import com.github.metnyov.addressbook.data.dao.AuthDaoImpl
import com.github.metnyov.addressbook.data.EncryptedPreferences
import com.github.metnyov.addressbook.data.database.AppDatabase
import com.github.metnyov.addressbook.data.repository.AuthRepositoryImpl
import com.github.metnyov.addressbook.data.repository.DirectoryRepositoryImpl
import com.github.metnyov.addressbook.data.repository.PhotoRepositoryImpl
import com.github.metnyov.addressbook.domain.source.Preferences
import com.github.metnyov.addressbook.domain.source.AuthDao
import com.github.metnyov.addressbook.data.dao.DepartmentDao
import com.github.metnyov.addressbook.data.dao.EmployeeDao
import com.github.metnyov.addressbook.domain.source.repository.AuthRepository
import com.github.metnyov.addressbook.domain.source.repository.DirectoryRepository
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

val sourceModule = DI.Module("source") {

    // Data
    bind<AppDatabase>() with singleton {
        Room.databaseBuilder(
            instance(),
            AppDatabase::class.java,
            "app-database"
        ).fallbackToDestructiveMigration().build()
    }
    bind<Preferences>() with singleton { EncryptedPreferences() }

    // DAO
    bind<AuthDao>() with singleton { AuthDaoImpl(instance()) }
    bind<DepartmentDao>() with singleton { instance<AppDatabase>().departmentDao() }
    bind<EmployeeDao>() with singleton { instance<AppDatabase>().employeeDao() }

    // Repositories
    bind<AuthRepository>() with singleton { AuthRepositoryImpl(instance(), instance()) }
    bind<DirectoryRepository>() with singleton {
        DirectoryRepositoryImpl(
            instance(),
            instance(),
            instance(),
            instance()
        )
    }
    bind<PhotoRepositoryImpl>() with singleton { PhotoRepositoryImpl(instance(), instance()) }
}