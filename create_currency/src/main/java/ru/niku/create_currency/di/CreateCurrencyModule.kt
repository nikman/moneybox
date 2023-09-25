package ru.niku.create_currency.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import ru.niku.create_currency.CreateCurrencyViewModelFactory


@Module
interface CreateCurrencyModule {

    @Binds
    fun bindsCreateCurrencyViewModelFactory(createCurrencyViewModelFactory: CreateCurrencyViewModelFactory): ViewModelProvider.Factory

}