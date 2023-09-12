package ru.niku.core

import ru.niku.coreapi.AppProvider
import ru.niku.coreapi.database.DatabaseProvider
import ru.niku.coreapi.network.NetworkProvider
import ru.niku.coreimpl.DaggerDatabaseComponent
import ru.niku.coreimpl.DaggerNetworkComponent

object CoreProviders {
    fun createDatabaseBuilder(appProvider: AppProvider): DatabaseProvider {
        return DaggerDatabaseComponent.builder().appProvider(appProvider).build()
    }

    fun createNetworkBuilder(): NetworkProvider {
        return DaggerNetworkComponent.builder().build()
    }

}