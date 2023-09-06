package ru.niku.core

import ru.niku.coreapi.AppProvider
import ru.niku.coreapi.DatabaseProvider
import ru.niku.coreapi.NetworkProvider
import ru.niku.coreimpl.DaggerDatabaseComponent
import ru.niku.coreimpl.DaggerNetworkComponent

object CoreProviders {
    fun createDatabaseBuilder(appProvider: AppProvider): DatabaseProvider {
        return DaggerDatabaseComponent.builder().appProvider(appProvider).build()
    }

    fun createNetworkBuilder(appProvider: AppProvider): NetworkProvider {
        return DaggerNetworkComponent.builder().appProvider(appProvider).build()
    }

}