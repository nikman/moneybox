package ru.niku.moneybox

import dagger.Binds
import dagger.Module
import dagger.Reusable
import ru.niku.coreapi.HomeMediator
import ru.niku.coreapi.MainMediator
import ru.niku.home.HomeMediatorImpl
import ru.niku.main.navigation.MainMediatorImpl

@Module
interface MediatorsBindings {

    @Binds
    @Reusable
    fun bindsMainMediator(mainMediatorImpl: MainMediatorImpl): MainMediator

    @Binds
    @Reusable
    fun bindsHomeMediator(homeMediatorImpl: HomeMediatorImpl): HomeMediator

}