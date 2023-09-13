package ru.niku.moneybox

import dagger.Binds
import dagger.Module
import dagger.Reusable
import ru.niku.coreapi.CurrenciesNavigator
import ru.niku.coreapi.HomeNavigator
import ru.niku.coreapi.MainNavigator
import ru.niku.coreapi.ReportsNavigator
import ru.niku.coreapi.WalletNavigator
import ru.niku.currencies.CurrenciesNavigatorImpl
import ru.niku.currencies.navigation.WalletNavigatorImpl
import ru.niku.home.HomeNavigatorImpl
import ru.niku.main.navigation.MainNavigatorImpl
import ru.niku.reports.navigation.ReportsNavigatorImpl

@Module
interface MediatorsBindings {

    @Binds
    @Reusable
    fun bindsMainMediator(mainMediatorImpl: MainNavigatorImpl): MainNavigator

    @Binds
    @Reusable
    fun bindsHomeMediator(homeMediatorImpl: HomeNavigatorImpl): HomeNavigator

    @Binds
    @Reusable
    fun bindsReportsMediator(reportsMediatorImpl: ReportsNavigatorImpl): ReportsNavigator

    @Binds
    @Reusable
    fun bindsCurrenciesMediator(currenciesMediatorImpl: CurrenciesNavigatorImpl): CurrenciesNavigator

    @Binds
    @Reusable
    fun bindsWalletMediator(walletMediatorImpl: WalletNavigatorImpl): WalletNavigator

}