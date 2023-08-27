package ru.niku.core

import ru.niku.coreapi.AppProvider
import ru.niku.coreapi.DatabaseProvider
import ru.niku.coreimpl.DaggerDatabaseComponent

object CoreProviders {
    fun createDatabaseBuilder(appProvider: AppProvider): DatabaseProvider {
        return DaggerDatabaseComponent.builder().appProvider(appProvider).build()
    }

}