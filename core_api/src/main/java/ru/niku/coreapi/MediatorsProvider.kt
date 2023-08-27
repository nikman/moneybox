package ru.niku.coreapi

import javax.inject.Provider

interface MediatorsProvider {

    fun provideMainMediator(): MainMediator

    fun provideHomeMediator(): HomeMediator

    fun mediatorsMap(): Map<Class<*>, @JvmSuppressWildcards Provider<Any>>
}