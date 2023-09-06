package ru.niku.coreimpl

import dagger.Component
import ru.niku.coreapi.AppProvider
import ru.niku.coreapi.DatabaseProvider
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [AppProvider::class],
    modules = [DatabaseModule::class]
)
interface DatabaseComponent : DatabaseProvider
