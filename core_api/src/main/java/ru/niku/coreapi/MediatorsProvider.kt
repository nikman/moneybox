package ru.niku.coreapi

import javax.inject.Provider

interface MediatorsProvider {

    fun provideMainMediator(): MainMediator

    fun provideHomeMediator(): HomeMediator

    fun provideReportsMediator(): ReportsMediator

    fun provideCurrenciesMediator(): CurrenciesMediator

    fun provideWalletMediator(): WalletMediator

    fun mediatorsMap(): Map<Class<*>, @JvmSuppressWildcards Provider<Any>>
}