package ru.niku.money_transaction.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.niku.money_transaction.MoneyTransactionViewModelFactory
import java.util.UUID

@Module
interface MoneyTransactionModule {

    @Binds
    fun bindsTransactionViewModelFactory(transactionViewModelFactory: MoneyTransactionViewModelFactory): ViewModelProvider.Factory

}