package ru.niku.coreapi

import androidx.room.Dao
import androidx.room.Query
import ru.niku.coreapi.dto.Account
import ru.niku.coreapi.dto.Currency

@Dao
interface MoneyboxDao {

    @Query("SELECT * FROM ACCOUNTS")
    suspend fun getAccounts(): List<Account>

    @Query("SELECT * FROM CURRENCIES")
    suspend fun getAllCurrencies(): List<Currency>

}