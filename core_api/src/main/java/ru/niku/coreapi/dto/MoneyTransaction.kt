package ru.niku.coreapi.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.util.Date

@Entity(tableName = "TRANSACTION")
@TypeConverters(value = [MoneyboxTypeConverter::class])
data class MoneyTransaction(
    @PrimaryKey(autoGenerate = true)
    val transaction_id: Long = 0,
    val transactionUuid: String = "",
    var date: Date = Date(),
    var type: Int = -1,
    var account_id: Long = 0,
    var amount: Double = 0.0,
    var note: String = ""
)
