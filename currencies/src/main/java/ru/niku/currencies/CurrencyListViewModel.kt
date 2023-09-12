package ru.niku.currencies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.map
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import ru.niku.coreapi.database.MoneyboxDao
import ru.niku.coreapi.network.WebApi
import ru.niku.coreapi.dto.Currency
import ru.niku.coreapi.dto.CurrencyModel
import javax.inject.Inject

class CurrencyListViewModel constructor(
    private val moneyboxDao: MoneyboxDao,
    private val webApi: WebApi
) : ViewModel() {

    private val _allCurrencies = MutableLiveData<List<Currency>>()

    val allCurrencies: LiveData<List<CurrencyModel>> =
        _allCurrencies.map {
            list -> list.map {
                CurrencyModel(
                    code = it.code,
                    name= it.name
                )
            }
        }

    private val viewModelJob = SupervisorJob()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun getAllCurrencies() {
        uiScope.launch {
            _allCurrencies.value = //currencyMemoryCache.getCurrency(...).ifEmpty {
                moneyboxDao.getAllCurrencies()
            //}.also {
            //    currencyMemoryCache.saveCurrency(...)
            //}
        }
    }

    suspend fun addCurrency() {
        var currency = Currency(0, "USD", "usd")
        moneyboxDao.addCurrency(currency)
        currency = Currency(0, "EUR", "eur")
        moneyboxDao.addCurrency(currency)
        currency = Currency(0, "KZT", "kzt")
        moneyboxDao.addCurrency(currency)
        currency = Currency(0, "CNY", "cny")
        moneyboxDao.addCurrency(currency)
    }

    suspend fun getCurrencyValue(currencyCode: String) = webApi.getCurrencyValue(currencyCode)

}

@Suppress("UNCHECKED_CAST")
class CurrencyListViewModelFactory @Inject constructor(
    private val moneyboxDao: MoneyboxDao,
    private val webApi: WebApi
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CurrencyListViewModel(moneyboxDao, webApi) as T
    }
}