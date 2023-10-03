package ru.niku.coreapi.dto

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import ru.niku.coreapi.TransactionType
import java.util.Date

@Entity(tableName = "TRANSACTIONS",
    foreignKeys = [
        ForeignKey(
            entity = Account::class,
            parentColumns = ["account_id"],
            childColumns = ["account_id"],
            onDelete = ForeignKey.SET_DEFAULT)
    ], indices = [Index("account_id")])
@TypeConverters(value = [MoneyboxTypeConverter::class])
data class MoneyTransaction(
    @PrimaryKey(autoGenerate = true)
    val transaction_id: Long = 0,
    val transactionUuid: String = "",
    var date: Date = Date(),
    var multiplier: Int = -1,
    var ttype: TransactionType = TransactionType.EXPENSE,
    //@ColumnInfo(name = "account_id", defaultValue = "")
    var account_id: Long = 0,
    var amount: Double = 0.0,
    var note: String = "",
    var category: String = ""
) {
    companion object {
        fun getAmount(amount: Double, ttype: TransactionType): Double {
            return if (ttype == TransactionType.EXPENSE) -1 * amount else amount
        }
        fun getMultiplier(ttype: TransactionType): Int {
            return if (ttype == TransactionType.EXPENSE) -1 else 1
        }
    }
}
