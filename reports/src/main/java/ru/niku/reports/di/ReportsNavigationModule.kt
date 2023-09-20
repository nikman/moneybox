package ru.niku.reports.di

import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import ru.niku.reports.navigation.ReportsNavigatorImpl
import ru.niku.reports_api.ReportsNavigator

@Module
interface ReportsNavigationModule {

    @Binds
    @IntoMap
    @ClassKey(ReportsNavigator::class)
    fun bindMediator(mediator: ReportsNavigatorImpl): Any

}