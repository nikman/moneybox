package ru.niku.money_transaction.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import ru.niku.money_transaction.MoneyTransactionViewModelFactory

@Module
interface MoneyTransactionModule {

    @Binds
    fun bindsTransactionViewModelFactory(transactionViewModelFactory: MoneyTransactionViewModelFactory): ViewModelProvider.Factory

}