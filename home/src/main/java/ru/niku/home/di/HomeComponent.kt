package ru.niku.home.di

import dagger.Component
import ru.niku.coreapi.ProvidersFacade
import ru.niku.coreapi.network.NetworkProvider
import ru.niku.home.home.HomeFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [HomeModule::class],
    dependencies = [ProvidersFacade::class]
)
interface HomeComponent {

    companion object {

        fun create(providersFacade: ProvidersFacade): HomeComponent {
            return DaggerHomeComponent
                .builder()
                .providersFacade(providersFacade)
                .build()
        }
    }

    fun inject(homeFragment: HomeFragment)
}