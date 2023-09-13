package ru.niku.home.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.niku.create_account_api.CreateAccountMediator
import ru.niku.home.home.HomeViewModelFactory
import javax.inject.Provider

@Module
interface HomeModule {

    /*@Binds
    @Singleton
    fun bindsCache(memoryCache: MemoryCacheImpl): MemoryCache*/

    @Binds
    fun bindsHomeViewModelFactory(homeViewModelFactory: HomeViewModelFactory): ViewModelProvider.Factory

    companion object{

        @Provides
        fun provideMediator1(map: Map<Class<*>, @JvmSuppressWildcards Provider<Any>>): CreateAccountMediator {
            return map[CreateAccountMediator::class.java]!!.get() as CreateAccountMediator
        }
    }
}