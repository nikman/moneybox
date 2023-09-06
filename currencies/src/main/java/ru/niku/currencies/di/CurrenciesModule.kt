package ru.niku.currencies.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import ru.niku.currencies.CurrencyListViewModelFactory

@Module
interface CurrenciesModule {

    @Binds
    fun bindsCurrenciesViewModelFactory(currencyListViewModelFactory: CurrencyListViewModelFactory):
            ViewModelProvider.Factory

}