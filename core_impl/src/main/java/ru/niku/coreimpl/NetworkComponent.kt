package ru.niku.coreimpl

import dagger.Component
import ru.niku.coreapi.AppProvider
import ru.niku.coreapi.NetworkProvider
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [AppProvider::class],
    modules = [NetworkModule::class]
)
interface NetworkComponent: NetworkProvider