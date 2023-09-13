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

    lateinit var moneyboxDatabase: MoneyboxDatabase

    private var rdc: RoomDatabase.Callback = object : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {

            var moneyboxDao: MoneyboxDao

            val viewModelJob = SupervisorJob()
            val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

            Executors.newSingleThreadScheduledExecutor().execute {

                val currencyRub =
                    Currency(0, "RUB", "rub")
                //storeCurrencyId(context, currencyRubUUID)

                uiScope.launch {
                    moneyboxDatabase.moneyboxDao().addCurrency(currencyRub)
                }

                val accountCash =
                    Account(
                        0, "Cash", "pocket",
                        true, true, 0, 0)

                uiScope.launch {
                    moneyboxDatabase.moneyboxDao().addAccount(accountCash)
                }

            }
        }

    }

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
            .addCallback(rdc)
            //.addMigrations(migrationFrom11To12)
            .build()
    }

}