package ru.niku.main.di

import dagger.Component
import ru.niku.coreapi.ProvidersFacade
import ru.niku.main.MainActivity

@Component(
    dependencies = [ProvidersFacade::class]
)
interface MainComponent {

    companion object {

        fun create(providersFacade: ProvidersFacade): MainComponent {
            return DaggerMainComponent.builder().providersFacade(providersFacade).build()
        }
    }

    fun inject(mainActivity: MainActivity)

}