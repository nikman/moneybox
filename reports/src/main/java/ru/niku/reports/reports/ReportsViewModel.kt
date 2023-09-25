package ru.niku.reports.reports

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.niku.coreapi.TransactionType
import ru.niku.coreapi.database.MoneyboxDao
import ru.niku.coreapi.dto.ExpencesByCategory
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

    private val _expencesByCategory = MutableLiveData<List<ExpencesByCategory>>()
    val expencesByCategory: LiveData<List<ExpencesByCategory>> = _expencesByCategory

    fun getExpencesByCategory() {
        viewModelScope.launch {
            _expencesByCategory.value = moneyboxDao.getExpencesByCategory(TransactionType.EXPENCE)
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

