package ru.niku.create_account.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import ru.niku.create_account.CreateAccountViewModelFactory

@Module
interface CreateAccountModule {

    /*@Binds
    @Singleton
    fun bindsCache(memoryCache: HabitsMemoryCacheImpl): HabitsMemoryCache*/

    @Binds
    fun bindsHomeViewModelFactory(homeViewModelFactory: CreateAccountViewModelFactory): ViewModelProvider.Factory

    /*companion object{

        @Provides
        fun provideMediator1(map: Map<Class<*>, @JvmSuppressWildcards Provider<Any>>): CreateAccountMediator {
            return map[CreateAccountMediator::class.java]!!.get() as CreateAccountMediator
        }
    }*/
}