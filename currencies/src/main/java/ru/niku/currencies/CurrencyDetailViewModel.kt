package ru.niku.currencies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.niku.coreapi.database.MoneyboxDao
import ru.niku.coreapi.dto.Currency
import javax.inject.Inject

class CurrencyDetailViewModel constructor(private val moneyboxDao: MoneyboxDao) : ViewModel() {

    fun saveCurrency(currency: Currency) {
        viewModelScope.launch {
            moneyboxDao.updateCurrency(currency)
        }
    }

}

@Suppress("UNCHECKED_CAST")
class CurrencyDetailViewModelFactory @Inject constructor(
    private val moneyboxDao: MoneyboxDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CurrencyDetailViewModel(moneyboxDao) as T
    }
}