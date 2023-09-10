package ru.niku.main.di

import dagger.Module
import dagger.Provides
import ru.niku.create_account_api.CreateAccountMediator
import javax.inject.Provider

@Module
interface MainModule {

    companion object{

        @Provides
        fun provideMediator1(map: Map<Class<*>, @JvmSuppressWildcards Provider<Any>>): CreateAccountMediator {
            return map[CreateAccountMediator::class.java]!!.get() as CreateAccountMediator
        }
    }

}