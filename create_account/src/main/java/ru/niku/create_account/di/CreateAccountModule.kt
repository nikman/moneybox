package ru.niku.create_account.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import ru.niku.create_account.CreateAccountViewModelFactory

@Module
interface CreateAccountModule {

    /*@Binds
    @Singleton
    fun bindsCache(memoryCache: MemoryCacheImpl): MemoryCache*/

    @Binds
    fun bindsCreateAccountViewModelFactory(createAccountViewModelFactory: CreateAccountViewModelFactory): ViewModelProvider.Factory

}