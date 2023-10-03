package ru.niku.coreapi

import javax.inject.Provider

interface MediatorsProvider {

    fun provideMainMediator(): MainNavigator

    fun provideHomeMediator(): HomeNavigator

    fun provideCurrenciesMediator(): CurrenciesNavigator

    fun mediatorsMap(): Map<Class<*>, @JvmSuppressWildcards Provider<Any>>
}