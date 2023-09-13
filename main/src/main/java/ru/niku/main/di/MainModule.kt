package ru.niku.main.di

import dagger.Module
import dagger.Provides
import ru.niku.create_account_api.CreateAccountMediator
import ru.niku.money_transaction_api.MoneyTransactionMediator
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
    }

}