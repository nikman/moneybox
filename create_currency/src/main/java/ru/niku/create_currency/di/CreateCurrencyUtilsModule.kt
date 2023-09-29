package ru.niku.create_currency.di

import dagger.Module
import dagger.Provides
import ru.niku.coreapi.dto.Currency

@Module
class CreateCurrencyUtilsModule {

    @Provides
    fun provideCurrency(): Currency = Currency()

}