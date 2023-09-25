package ru.niku.coreimpl

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.niku.coreapi.database.MoneyboxDatabaseContract
import ru.niku.coreapi.dto.Account
import ru.niku.coreapi.dto.Currency
import ru.niku.coreapi.dto.MoneyTransaction
import ru.niku.coreapi.dto.Turnovers

@Database(
    entities = [Account::class, Currency::class, Turnovers::class, MoneyTransaction::class],
    version = 20,
    exportSchema = false)
@TypeConverters(MoneyboxTypeConverters::class)
abstract class MoneyboxDatabase : RoomDatabase(), MoneyboxDatabaseContract