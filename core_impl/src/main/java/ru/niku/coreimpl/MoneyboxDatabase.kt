package ru.niku.coreimpl

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.niku.coreapi.database.MoneyboxDatabaseContract
import ru.niku.coreapi.dto.Account
import ru.niku.coreapi.dto.Currency
import ru.niku.coreapi.dto.Turnovers

@Database(entities = [Account::class, Currency::class, Turnovers::class], version = 11, exportSchema = false)
abstract class MoneyboxDatabase : RoomDatabase(), MoneyboxDatabaseContract