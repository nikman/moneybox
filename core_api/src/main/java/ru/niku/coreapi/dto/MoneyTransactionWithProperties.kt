package ru.niku.coreapi.dto

import androidx.room.Embedded
import androidx.room.Relation

data class MoneyTransactionWithProperties constructor(

    @Embedded
    val transaction: MoneyTransaction,

    @Relation(parentColumn = "account_id", entityColumn = "account_id")
    val accountSource: Account
)