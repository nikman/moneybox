package ru.niku.reports.reports

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.niku.coreapi.TransactionType
import ru.niku.coreapi.database.MoneyboxDao
import ru.niku.coreapi.dto.ExpensesByCategory
import ru.niku.coreapi.dto.MoneyTransactionWithProperties
import javax.inject.Inject

class ReportsViewModel constructor(private val moneyboxDao: MoneyboxDao): ViewModel() {

    private val _topTransactions = MutableLiveData<List<MoneyTransactionWithProperties>>()
    val topTransactions: LiveData<List<MoneyTransactionWithProperties>> = _topTransactions

    fun getTopTransactions() {
        viewModelScope.launch {
            _topTransactions.value = moneyboxDao.getTopTransactions()
        }
    }

    private val _expensesByCategory = MutableLiveData<List<ExpensesByCategory>>()
    val expensesByCategory: LiveData<List<ExpensesByCategory>> = _expensesByCategory

    fun getExpencesByCategory() {
        viewModelScope.launch {
            _expensesByCategory.value = moneyboxDao.getExpensesByCategory(TransactionType.EXPENSE)
        }
    }

}

@Suppress("UNCHECKED_CAST")
class ReportsViewModelFactory @Inject constructor(
    private val moneyboxDao: MoneyboxDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ReportsViewModel(moneyboxDao) as T
    }
}

