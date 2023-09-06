package ru.niku.coreapi

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ru.niku.coreapi.dto.Account
import ru.niku.coreapi.dto.Currency
import ru.niku.coreapi.dto.Turnovers

@Dao
interface MoneyboxDao {

    @Query("SELECT * FROM ACCOUNTS")
    suspend fun getAccounts(): List<Account>

    @Query("SELECT * FROM CURRENCIES")
    suspend fun getAllCurrencies(): List<Currency>

    @Query("SELECT SUM(amount) FROM TURNOVERS")
    suspend fun getOverallBalance(): Double

    @Insert
    suspend fun addAccount(account: Account)

    @Insert
    suspend fun addCurrency(currency: Currency)

    @Update
    suspend fun updateCurrency(currency: Currency)

    @Insert
    suspend fun addTurnover(turnover: Turnovers)

}