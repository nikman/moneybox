package ru.niku.money_transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.niku.coreapi.database.MoneyboxDao
import ru.niku.coreapi.dto.MoneyTransaction
import javax.inject.Inject

class MoneyTransactionViewModel constructor(private val moneyboxDao: MoneyboxDao): ViewModel() {

    fun addTransaction(transaction: MoneyTransaction) {
        viewModelScope.launch {
            moneyboxDao.addTransaction(transaction)
        }
    }

}

@Suppress("UNCHECKED_CAST")
class MoneyTransactionViewModelFactory @Inject constructor(
    private val moneyboxDao: MoneyboxDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MoneyTransactionViewModel(moneyboxDao) as T
    }
}