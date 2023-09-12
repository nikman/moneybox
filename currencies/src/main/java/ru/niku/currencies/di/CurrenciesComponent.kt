package ru.niku.currencies.di

import dagger.Component
import ru.niku.coreapi.ProvidersFacade
import ru.niku.coreapi.network.NetworkProvider
import ru.niku.currencies.CurrencyListFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [CurrenciesModule::class],
    dependencies = [ProvidersFacade::class, NetworkProvider::class]
)
interface CurrenciesComponent {

    companion object {

        fun create(providersFacade: ProvidersFacade, networkProvider: NetworkProvider): CurrenciesComponent {
            return DaggerCurrenciesComponent
                .builder()
                .providersFacade(providersFacade)
                .networkProvider(networkProvider)
                .build()
        }
    }

    fun inject(currencyListFragment: CurrencyListFragment)

}