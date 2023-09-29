package ru.niku.create_account.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import ru.niku.create_account.CreateAccountViewModelFactory

@Module
interface CreateAccountModule {

    @Binds
    fun bindsCreateAccountViewModelFactory(createAccountViewModelFactory: CreateAccountViewModelFactory): ViewModelProvider.Factory

}