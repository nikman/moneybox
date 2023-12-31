package ru.niku.coreapi.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.niku.coreapi.TransactionType
import java.util.Date

@Entity(tableName = "TURNOVERS")
data class Turnovers(
    @PrimaryKey(autoGenerate = true) val turnoverId: Long = 0,
    val transactionUuid: String = "",
    val transactionId: Long = 0,
    var date: Date = Date(),
    var accountId: Long = 0,
    var amount: Double = 0.0,
    var multiplier: Int = -1,
    var ttype: TransactionType = TransactionType.EXPENCE,
    var category: String = ""
)
