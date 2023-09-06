package ru.niku.coreimpl

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.niku.coreapi.MoneyboxDao
import ru.niku.coreapi.MoneyboxDatabaseContract
import ru.niku.coreapi.dto.Currency
import java.util.UUID
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
            //.addMigrations(migrationFrom11To12)
            .build()
    }

}