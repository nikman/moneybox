package ru.niku.coreimpl

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.niku.coreapi.MoneyboxDatabaseContract
import ru.niku.coreapi.dto.Account

@Database(entities = [Account::class], version = 1, exportSchema = false)
abstract class MoneyboxDatabase : RoomDatabase(), MoneyboxDatabaseContract