package ru.niku.coreapi.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "CURRENCIES")
@TypeConverters(value = [MoneyboxTypeConverter::class])
data class Currency(
    @PrimaryKey(autoGenerate = true) val currecyId: Long = 0,
    val rub: Double // value
)
