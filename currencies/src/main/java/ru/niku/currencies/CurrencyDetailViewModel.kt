package ru.niku.currencies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.niku.coreapi.MoneyboxDao
import javax.inject.Inject

class CurrencyDetailViewModel constructor(private val moneyboxDao: MoneyboxDao) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is currencies Fragment"
    }
    val text: LiveData<String> = _text

}

@Suppress("UNCHECKED_CAST")
class CurrencyDetailViewModelFactory @Inject constructor(
    private val moneyboxDao: MoneyboxDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CurrencyListViewModel(moneyboxDao) as T
    }
}