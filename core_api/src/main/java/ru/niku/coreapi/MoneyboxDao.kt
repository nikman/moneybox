package ru.niku.coreapi

import androidx.room.Dao
import androidx.room.Query
import ru.niku.coreapi.dto.Account

@Dao
interface MoneyboxDao {

    @Query("SELECT * FROM ACCOUNTS")
    suspend fun getAccounts(): List<Account>

}