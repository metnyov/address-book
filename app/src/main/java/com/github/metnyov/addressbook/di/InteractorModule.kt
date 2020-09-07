package com.github.metnyov.addressbook.di

import com.github.metnyov.addressbook.domain.interactor.*
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

val interactorModule = DI.Module("interactor") {

    bind() from singleton { LoginUseCase(instance()) }
    bind() from singleton { LogoutUseCase(instance()) }
    bind() from singleton { LoadDirectoryUseCase(instance()) }
    bind() from singleton { GetDepartmentsUseCase(instance()) }
    bind() from singleton { GetEmployeesUseCase(instance()) }
    bind() from singleton { GetPhotoByEmployeeIdUseCase(instance()) }
}