package ru.niku.money_transaction

import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import ru.niku.money_transaction_api.MoneyTransactionMediator

@Module
interface MoneyTransactionNavigationModule {

    @Binds
    @IntoMap
    @ClassKey(MoneyTransactionMediator::class)
    fun bindMediator(mediator: MoneyTransactionMediatorImpl): Any

}