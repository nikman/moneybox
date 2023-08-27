package ru.niku.coreimpl

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.Reusable
import ru.niku.coreapi.MoneyboxDao
import ru.niku.coreapi.MoneyboxDatabaseContract
import javax.inject.Singleton

private const val DATABASE_NAME = "MONEYBOX_DB"

@Module
class DatabaseModule {

    @Provides
    @Reusable
    fun provideMoneyboxDao(moneyboxDatabaseContract: MoneyboxDatabaseContract): MoneyboxDao {
        return moneyboxDatabaseContract.moneyboxDao()
    }

    @Provides
    @Singleton
    fun provideMoneyboxDatabase(context: Context): MoneyboxDatabaseContract {
        return Room.databaseBuilder(
            context,
            MoneyboxDatabase::class.java, DATABASE_NAME
        ).build()
    }
}