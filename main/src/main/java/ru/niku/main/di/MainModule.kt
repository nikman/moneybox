package ru.niku.main.di

import dagger.Module
import dagger.Provides
import ru.niku.create_account_api.CreateAccountMediator
import ru.niku.create_currency_api.CreateCurrencyMediator
import ru.niku.money_transaction_api.MoneyTransactionMediator
import ru.niku.reports_api.ReportsNavigator
import javax.inject.Provider

@Module
interface MainModule {

    companion object{

        @Provides
        fun provideMediator1(map: Map<Class<*>, @JvmSuppressWildcards Provider<Any>>): CreateAccountMediator {
            return map[CreateAccountMediator::class.java]!!.get() as CreateAccountMediator
        }

        @Provides
        fun provideMediator2(map: Map<Class<*>, @JvmSuppressWildcards Provider<Any>>): MoneyTransactionMediator {
            return map[MoneyTransactionMediator::class.java]!!.get() as MoneyTransactionMediator
        }

        @Provides
        fun provideMediator3(map: Map<Class<*>, @JvmSuppressWildcards Provider<Any>>): CreateCurrencyMediator {
            return map[CreateCurrencyMediator::class.java]!!.get() as CreateCurrencyMediator
        }

        @Provides
        fun provideMediator4(map: Map<Class<*>, @JvmSuppressWildcards Provider<Any>>): ReportsNavigator {
            return map[ReportsNavigator::class.java]!!.get() as ReportsNavigator
        }
    }

}