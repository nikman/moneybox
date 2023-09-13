package ru.niku.coreapi.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "TRANSACTION")
@TypeConverters(value = [MoneyboxTypeConverter::class])
data class MoneyTransaction(
    @PrimaryKey(autoGenerate = true)
    val transaction_id: Long = 0,
    val account_id: Long,
    val amount: Double
)
