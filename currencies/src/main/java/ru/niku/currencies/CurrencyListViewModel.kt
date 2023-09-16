package ru.niku.currencies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.niku.coreapi.database.MoneyboxDao
import ru.niku.coreapi.dto.Currency
import ru.niku.coreapi.dto.CurrencyModel
import ru.niku.coreapi.network.WebApi
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

    /*private val viewModelJob = SupervisorJob()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)*/

    fun getAllCurrencies() {
        viewModelScope.launch {
            _allCurrencies.value = //currencyMemoryCache.getCurrency(...).ifEmpty {
                moneyboxDao.getAllCurrencies()
            //}.also {
            //    currencyMemoryCache.saveCurrency(...)
            //}
        }
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