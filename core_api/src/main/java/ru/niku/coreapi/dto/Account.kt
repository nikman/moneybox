package ru.niku.coreapi.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "ACCOUNTS")
@TypeConverters(value = [MoneyboxTypeConverter::class])
data class Account(
    //@PrimaryKey val account_id: UUID = UUID.randomUUID(),

    //@NotNull
    //@ColumnInfo(name = "currency_id", defaultValue = UUID_CURRENCY_RUB)
    //var currency_id: UUID,
    @PrimaryKey(autoGenerate = true) val account_id: Long = 0,
    var title: String = "",
    var note: String = "",
    var is_active: Boolean = true,
    var is_included_into_totals: Boolean = true,
    var sort_order: Int = 0,
    val account_external_id: Int = 0
) {
    override fun toString(): String {
        return "$title $note"
    }
}