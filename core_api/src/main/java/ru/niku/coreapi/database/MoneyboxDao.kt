package ru.niku.coreapi.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import ru.niku.coreapi.TransactionType
import ru.niku.coreapi.dto.Account
import ru.niku.coreapi.dto.AccountsWithBalance
import ru.niku.coreapi.dto.Currency
import ru.niku.coreapi.dto.ExpencesByCategory
import ru.niku.coreapi.dto.MoneyTransaction
import ru.niku.coreapi.dto.MoneyTransactionWithProperties
import ru.niku.coreapi.dto.Turnovers

@Dao
interface MoneyboxDao {

    @Query("SELECT * FROM ACCOUNTS")
    suspend fun getAllAccounts(): List<Account>

    @Query("SELECT * FROM ACCOUNTS WHERE is_active")
    suspend fun getAllActiveAccounts(): List<Account>

    @Query("SELECT * FROM CURRENCIES")
    suspend fun getAllCurrencies(): List<Currency>

    @Query("SELECT SUM(amount) FROM TURNOVERS")
    suspend fun getOverallBalance(): Double

    @Query("SELECT SUM(amount) FROM TURNOVERS WHERE accountId=:accountId")
    suspend fun getAccountBalance(accountId: Long): Double

    @Transaction
    @Query("SELECT accountId, SUM(amount) as balance FROM TURNOVERS GROUP BY accountId")
    suspend fun getAccountsBalance(): List<AccountsWithBalance>

    @Query("SELECT * FROM TRANSACTIONS ORDER BY date DESC LIMIT 20")
    suspend fun getTopTransactions(): List<MoneyTransactionWithProperties>

    @Query("SELECT * FROM TRANSACTIONS ORDER BY amount DESC")
    suspend fun getTopBiggestTransactions(): List<MoneyTransactionWithProperties>

    @Query("SELECT category, SUM(amount) as amount FROM TURNOVERS WHERE ttype = :ttype GROUP BY category ORDER BY SUM(amount)")
    suspend fun getExpencesByCategory(ttype: TransactionType): List<ExpencesByCategory>

    @Insert
    suspend fun addAccount(account: Account)

    @Insert
    suspend fun addTransaction(transaction: MoneyTransaction)

    @Insert
    suspend fun addCurrency(currency: Currency)

    @Update
    suspend fun updateCurrency(currency: Currency)

    @Insert
    suspend fun addTurnover(turnover: Turnovers)

}