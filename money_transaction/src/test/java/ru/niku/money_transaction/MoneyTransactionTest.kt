package ru.niku.money_transaction

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.niku.coreapi.TransactionType
import ru.niku.coreapi.dto.MoneyTransaction

class MoneyTransactionTest {

    @Test
    fun checkMultiplier() {
        val expectedMultiplier = -1
        val actualMultiplier = MoneyTransaction.getMultiplier(TransactionType.EXPENSE)

        assertEquals(expectedMultiplier, actualMultiplier)
    }

    @Test
    fun checkAmount() {
        val expectedAmount = -100.0
        val actualAmount = MoneyTransaction.getAmount(100.0, TransactionType.EXPENSE)

        assertEquals(expectedAmount, actualAmount, 0.1)
    }

}