package ru.niku.coreapi.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName

@Entity(tableName = "CURRENCIES")
@TypeConverters(value = [MoneyboxTypeConverter::class])
data class Currency(
    @PrimaryKey(autoGenerate = true) val currecyId: Long = 0,
    var name: String = "",
    var code: String = "",
    val rub: Double = 0.0,
    @SerializedName("conversion_rate")
    val conversionrate: Double = 0.0
)

