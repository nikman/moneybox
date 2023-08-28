package ru.niku.reports.di

import dagger.Component
import ru.niku.coreapi.ProvidersFacade
import ru.niku.reports.reports.ReportsFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ReportsModule::class],
    dependencies = [ProvidersFacade::class]
)
interface ReportsComponent {

    companion object {

        fun create(providersFacade: ProvidersFacade): ReportsComponent {
            return DaggerReportsComponent
                .builder()
                .providersFacade(providersFacade)
                .build()
        }
    }

    fun inject(homeFragment: ReportsFragment)

}