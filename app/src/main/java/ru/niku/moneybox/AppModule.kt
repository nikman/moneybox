package ru.niku.moneybox

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Module
    companion object {

        private const val PREFS_NAME = "MONEYBOX_PREFS"

        @JvmStatic
        @Provides
        @Singleton
        fun provideSharedPreferences(context: Context): SharedPreferences =
            context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }
}