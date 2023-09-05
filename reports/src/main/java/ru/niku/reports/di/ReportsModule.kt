package ru.niku.reports.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import ru.niku.reports.reports.ReportsViewModelFactory

@Module
interface ReportsModule {

    /*@Binds
    @Singleton
    fun bindsCache(memoryCache: HabitsMemoryCacheImpl): HabitsMemoryCache*/

    @Binds
    fun bindsReportsViewModelFactory(reportsViewModelFactory: ReportsViewModelFactory):
            ViewModelProvider.Factory

    /*companion object{

        @Provides
        fun provideMediator1(map: Map<Class<*>, @JvmSuppressWildcards Provider<Any>>): CreateAccountMediator {
            return map[CreateAccountMediator::class.java]!!.get() as CreateAccountMediator
        }
    }*/

}