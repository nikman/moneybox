package ru.niku.money_transaction

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.niku.coreapi.database.MoneyboxDao
import ru.niku.coreapi.dto.Account
import ru.niku.coreapi.dto.MoneyTransaction
import ru.niku.coreapi.dto.Turnovers
import javax.inject.Inject

class MoneyTransactionViewModel constructor(private val moneyboxDao: MoneyboxDao): ViewModel() {

    fun addTransaction(transaction: MoneyTransaction) {
        viewModelScope.launch {
            moneyboxDao.addTransaction(transaction)
        }
    }

    fun addTurnover(turnover: Turnovers) {
        viewModelScope.launch {
            moneyboxDao.addTurnover(turnover)
        }
    }

    private val _allActiveAccounts = MutableLiveData<List<Account>>()
    val allActiveAccounts: LiveData<List<Account>> = _allActiveAccounts

    fun getAllActiveAccounts() {
        viewModelScope.launch {
            _allActiveAccounts.value =
                moneyboxDao.getAllActiveAccounts()
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