package ru.niku.moneybox

import dagger.Binds
import dagger.Module
import dagger.Reusable
import ru.niku.coreapi.HomeMediator
import ru.niku.coreapi.MainMediator
import ru.niku.coreapi.ReportsMediator
import ru.niku.home.HomeMediatorImpl
import ru.niku.main.navigation.MainMediatorImpl
import ru.niku.reports.ReportsMediatorImpl

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

}