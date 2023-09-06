package ru.niku.currencies.di

import dagger.Component
import ru.niku.coreapi.ProvidersFacade
import ru.niku.currencies.CurrencyListFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [CurrenciesModule::class],
    dependencies = [ProvidersFacade::class]
)
interface CurrenciesComponent {

    companion object {

        fun create(providersFacade: ProvidersFacade): CurrenciesComponent {
            return DaggerCurrenciesComponent
                .builder()
                .providersFacade(providersFacade)
                .build()
        }
    }

    fun inject(currencyListFragment: CurrencyListFragment)

}