package ru.niku.create_account.di

import dagger.Component
import ru.niku.coreapi.ProvidersFacade
import ru.niku.create_account.CreateAccountFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [CreateAccountModule::class, CreateAccountUtilsModule::class],
    dependencies = [ProvidersFacade::class]
)
interface CreateAccountComponent {

    companion object {

        fun create(providersFacade: ProvidersFacade): CreateAccountComponent {
            return DaggerCreateAccountComponent.builder()
                .providersFacade(providersFacade)
                .build()
        }
    }

    fun inject(createAccountFragment: CreateAccountFragment)
}