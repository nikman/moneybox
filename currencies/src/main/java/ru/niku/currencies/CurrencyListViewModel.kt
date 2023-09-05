package ru.niku.currencies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import ru.niku.coreapi.MoneyboxDao
import ru.niku.coreapi.dto.Currency
import javax.inject.Inject
import androidx.lifecycle.map
import ru.niku.coreapi.dto.CurrencyModel

class CurrencyListViewModel constructor(private val moneyboxDao: MoneyboxDao) : ViewModel() {

    /*private val _text = MutableLiveData<String>().apply {
        value = "This is currencies Fragment"
    }
    val text: LiveData<String> = _text*/

    private val _allCurrencies = MutableLiveData<List<Currency>>()

    val allCurrencies: LiveData<List<CurrencyModel>> =
        _allCurrencies.map {
            list -> list.map { CurrencyModel(it.currecyId.toString(), it.currecyId.toString()) }
        }

    private val viewModelJob = SupervisorJob()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun getAllCurrencies() {
        uiScope.launch {
            _allCurrencies.value = //currencyMemoryCache.getHabits(day).ifEmpty {
                moneyboxDao.getAllCurrencies()
            //}.also {
            //    habitsMemoryCache.saveHabit(day, it)
            //}
        }
    }

}

@Suppress("UNCHECKED_CAST")
class CurrencyListViewModelFactory @Inject constructor(
    private val moneyboxDao: MoneyboxDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CurrencyListViewModel(moneyboxDao) as T
    }
}