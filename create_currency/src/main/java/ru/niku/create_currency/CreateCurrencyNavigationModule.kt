package ru.niku.create_currency

import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import ru.niku.create_currency_api.CreateCurrencyMediator

@Module
interface CreateCurrencyNavigationModule {

    @Binds
    @IntoMap
    @ClassKey(CreateCurrencyMediator::class)
    fun bindMediator(mediator: CreateCurrencyMediatorImpl): Any

}