package ru.niku.moneybox

import dagger.Binds
import dagger.Module
import dagger.Reusable
import ru.niku.coreapi.CurrenciesMediator
import ru.niku.coreapi.HomeMediator
import ru.niku.coreapi.MainMediator
import ru.niku.coreapi.ReportsMediator
import ru.niku.coreapi.WalletMediator
import ru.niku.currencies.CurrenciesMediatorImpl
import ru.niku.currencies.navigation.WalletMediatorImpl
import ru.niku.home.HomeMediatorImpl
import ru.niku.main.navigation.MainMediatorImpl
import ru.niku.reports.navigation.ReportsMediatorImpl

@Module
interface MediatorsBindings {

    @Binds
    @Reusable
    fun bindsMainMediator(mainMediatorImpl: MainMediatorImpl): MainMediator

    @Binds
    @Reusable
    fun bindsHomeMediator(homeMediatorImpl: HomeMediatorImpl): HomeMediator

    @Binds
    @Reusable
    fun bindsReportsMediator(reportsMediatorImpl: ReportsMediatorImpl): ReportsMediator

    @Binds
    @Reusable
    fun bindsCurrenciesMediator(currenciesMediatorImpl: CurrenciesMediatorImpl): CurrenciesMediator

    @Binds
    @Reusable
    fun bindsWalletMediator(walletMediatorImpl: WalletMediatorImpl): WalletMediator

}