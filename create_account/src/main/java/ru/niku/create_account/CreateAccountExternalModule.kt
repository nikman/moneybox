package ru.niku.create_account

import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import ru.niku.create_account_api.CreateAccountMediator

@Module
interface CreateAccountExternalModule {

    @Binds
    @IntoMap
    @ClassKey(CreateAccountMediator::class)
    fun bindMediator(mediator: CreateAccountMediatorImpl): Any

}