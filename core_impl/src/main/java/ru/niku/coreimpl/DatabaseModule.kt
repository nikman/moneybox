package ru.niku.coreimpl

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import dagger.Reusable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import ru.niku.coreapi.database.MoneyboxDao
import ru.niku.coreapi.database.MoneyboxDatabaseContract
import ru.niku.coreapi.dto.Account
import ru.niku.coreapi.dto.Currency
import java.util.concurrent.Executors
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
            MoneyboxDatabase::class.java,
            DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

}