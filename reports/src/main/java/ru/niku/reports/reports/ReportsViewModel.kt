package ru.niku.reports.reports

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.niku.coreapi.database.MoneyboxDao
import ru.niku.coreapi.dto.MoneyTransactionWithProperties
import javax.inject.Inject

class ReportsViewModel constructor(private val moneyboxDao: MoneyboxDao): ViewModel() {

    private val _allTransactions = MutableLiveData<List<MoneyTransactionWithProperties>>()

    val allTransactions: LiveData<List<MoneyTransactionWithProperties>> = _allTransactions

    fun getTopTransactions() {
        viewModelScope.launch {
            _allTransactions.value = moneyboxDao.getTopTransactions()
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

