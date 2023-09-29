package ru.niku.money_transaction.di

import dagger.Component
import ru.niku.coreapi.ProvidersFacade
import ru.niku.money_transaction.MoneyTransactionFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [MoneyTransactionModule::class, MoneyTransactionUtilsModule::class],
    dependencies = [ProvidersFacade::class]
)
interface MoneyTransactionComponent {

    companion object {

        fun create(providersFacade: ProvidersFacade): MoneyTransactionComponent {
            return DaggerMoneyTransactionComponent.builder()
                .providersFacade(providersFacade)
                .build()
        }
    }

    fun inject(moneyTransactionFragment: MoneyTransactionFragment)
}