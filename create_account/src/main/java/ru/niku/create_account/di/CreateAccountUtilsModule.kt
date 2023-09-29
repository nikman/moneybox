package ru.niku.create_account.di

import dagger.Module
import dagger.Provides
import ru.niku.coreapi.dto.Account

@Module
class CreateAccountUtilsModule {

    @Provides
    fun provideAccount(): Account = Account()

}