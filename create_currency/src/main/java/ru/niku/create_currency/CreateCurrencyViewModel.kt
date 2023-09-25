package ru.niku.create_currency

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.niku.coreapi.database.MoneyboxDao
import ru.niku.coreapi.dto.Currency
import javax.inject.Inject

class CreateCurrencyViewModel constructor(private val moneyboxDao: MoneyboxDao): ViewModel() {

    fun addCurrency(currency: Currency) {
        viewModelScope.launch {
            moneyboxDao.addCurrency(currency)
        }
    }

}

@Suppress("UNCHECKED_CAST")
class CreateCurrencyViewModelFactory @Inject constructor(
    private val moneyboxDao: MoneyboxDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CreateCurrencyViewModel(moneyboxDao) as T
    }
}