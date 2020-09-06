package com.github.metnyov.addressbook.di

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

val presentationModule = DI.Module("presentation") {

    // Navigation
    val cicerone: Cicerone<Router> = Cicerone.create()

    bind() from singleton { cicerone.router }
    bind() from singleton { cicerone.navigatorHolder }
}