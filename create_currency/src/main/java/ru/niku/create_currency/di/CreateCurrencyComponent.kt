package ru.niku.create_currency.di

import dagger.Component
import ru.niku.coreapi.ProvidersFacade
import ru.niku.create_currency.CreateCurrencyFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [CreateCurrencyModule::class, CreateCurrencyUtilsModule::class],
    dependencies = [ProvidersFacade::class]
)
interface CreateCurrencyComponent {

    companion object {

        fun create(providersFacade: ProvidersFacade): CreateCurrencyComponent {
            return DaggerCreateCurrencyComponent.builder()
                .providersFacade(providersFacade)
                .build()
        }
    }

    fun inject(createCurrencyFragment: CreateCurrencyFragment)
}