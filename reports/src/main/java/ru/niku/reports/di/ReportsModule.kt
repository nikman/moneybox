package ru.niku.reports.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.niku.reports.reports.ReportsViewModelFactory
import javax.inject.Provider

@Module
interface ReportsModule {

    /*@Binds
    @Singleton
    fun bindsCache(memoryCache: HabitsMemoryCacheImpl): HabitsMemoryCache*/

    @Binds
    fun bindsReportsViewModelFactory(homeViewModelFactory: ReportsViewModelFactory): ViewModelProvider.Factory

    /*companion object{

        @Provides
        fun provideMediator1(map: Map<Class<*>, @JvmSuppressWildcards Provider<Any>>): CreateAccountMediator {
            return map[CreateAccountMediator::class.java]!!.get() as CreateAccountMediator
        }
    }*/

}