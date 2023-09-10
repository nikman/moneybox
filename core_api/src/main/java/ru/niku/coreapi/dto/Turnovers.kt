package ru.niku.coreapi.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TURNOVERS")
data class Turnovers(
    @PrimaryKey(autoGenerate = true) val turnoverId: Long = 0,
    val transactionId: Long = 0,
    val accountId: Long = 0,
    val amount: Double = 0.0
)
