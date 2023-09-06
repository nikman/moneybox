package ru.niku.home.home

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
import ru.niku.coreapi.dto.Turnovers
import javax.inject.Inject

class HomeViewModel constructor(private val moneyboxDao: MoneyboxDao) : ViewModel() {

    private val _text = MutableLiveData<String>()

    val text: LiveData<String> = _text

    private val viewModelJob = SupervisorJob()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun getOverallBalance() {
        uiScope.launch {
            _text.value = //currencyMemoryCache.getCurrency(...).ifEmpty {
                moneyboxDao.getOverallBalance().toString()
            //}.also {
            //    currencyMemoryCache.saveCurrency(...)
            //}
        }
    }

    suspend fun addTurnovers() {
        var turnover = Turnovers(0, 1, 1, 250.0)
        moneyboxDao.addTurnover(turnover)
        turnover = Turnovers(0, 2, 1, 2500.0)
        moneyboxDao.addTurnover(turnover)
        turnover = Turnovers(0, 3, 1, 2550.0)
        moneyboxDao.addTurnover(turnover)
        turnover = Turnovers(0, 4, 1, 6500.0)
        moneyboxDao.addTurnover(turnover)
    }

}

@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory @Inject constructor(
    private val moneyboxDao: MoneyboxDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(moneyboxDao) as T
    }
}
