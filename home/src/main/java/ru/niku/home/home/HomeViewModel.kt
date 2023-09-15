package ru.niku.home.home

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import ru.niku.coreapi.database.MoneyboxDao
import ru.niku.coreapi.dto.Account
import ru.niku.coreapi.dto.Turnovers
import ru.niku.create_account_api.CreateAccountMediator
import java.util.Date
import javax.inject.Inject

class HomeViewModel
constructor(
    private val moneyboxDao: MoneyboxDao,
    private val createAccountMediator: CreateAccountMediator) : ViewModel() {

    private val _text = MutableLiveData<String>()

    val text: LiveData<String> = _text

    private val viewModelJob = SupervisorJob()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _allAccounts = MutableLiveData<List<Account>>()

    val allAccounts: LiveData<List<Account>> = _allAccounts

    fun getOverallBalance() {
        uiScope.launch {
            _text.value = moneyboxDao.getOverallBalance().toString()
        }
    }

    fun getAllAccounts() {
        uiScope.launch {
            _allAccounts.value =
                moneyboxDao.getAllAccounts()
        }
    }

    fun openCreateAccountScreen(containerId: Int, fragmentManager: FragmentManager) {
        createAccountMediator.openCreateAccountScreen(containerId, fragmentManager)
    }

    suspend fun addTurnovers() {
        var turnover = Turnovers(0, "0", 1, Date(), 1, 250.0)
        moneyboxDao.addTurnover(turnover)
        turnover = Turnovers(0,"", 2, Date(),1, 2500.0)
        moneyboxDao.addTurnover(turnover)
        turnover = Turnovers(0,"", 3, Date(),1, 2550.0)
        moneyboxDao.addTurnover(turnover)
        turnover = Turnovers(0, "", 4, Date(),1, 6500.0)
        moneyboxDao.addTurnover(turnover)
    }

}

@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory @Inject constructor(
    private val moneyboxDao: MoneyboxDao,
    private val createAccountMediator: CreateAccountMediator
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(moneyboxDao, createAccountMediator) as T
    }
}
