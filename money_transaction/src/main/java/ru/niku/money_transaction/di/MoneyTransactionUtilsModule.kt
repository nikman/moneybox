package ru.niku.money_transaction.di

import dagger.Module
import dagger.Provides
import ru.niku.coreapi.dto.MoneyTransaction
import java.util.UUID

@Module
class MoneyTransactionUtilsModule {

    @Provides
    fun provideUUID(): UUID = UUID.randomUUID()

}