package ru.niku.reports.reports

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.niku.coreapi.database.MoneyboxDao
import javax.inject.Inject

class ReportsViewModel constructor(private val moneyboxDao: MoneyboxDao): ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is reports Fragment"
    }
    val text: LiveData<String> = _text

}

@Suppress("UNCHECKED_CAST")
class ReportsViewModelFactory @Inject constructor(
    private val moneyboxDao: MoneyboxDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ReportsViewModel(moneyboxDao) as T
    }
}

