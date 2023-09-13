package ru.niku.coreimpl

import dagger.Component
import ru.niku.coreapi.network.NetworkProvider
import javax.inject.Singleton

@Singleton
@Component(
    modules = [NetworkModule::class]
)
interface NetworkComponent: NetworkProvider