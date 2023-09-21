package ru.niku.home.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.niku.coreapi.database.MoneyboxDao
import ru.niku.coreapi.dto.Account
import ru.niku.coreapi.dto.AccountsWithBalance
import javax.inject.Inject

class HomeViewModel(
    private val moneyboxDao: MoneyboxDao
) : ViewModel() {

    private val _text = MutableLiveData<String>()
    val text: LiveData<String> = _text

    private val _accountsBalance = MutableLiveData<List<AccountsWithBalance>>()
    val accountsBalance: LiveData<List<AccountsWithBalance>> = _accountsBalance

    private val _allAccounts = MutableLiveData<List<Account>>()
    val allAccounts: LiveData<List<Account>> = _allAccounts

    fun getOverallBalance() {
        viewModelScope.launch {
            _text.value = moneyboxDao.getOverallBalance().toString()
        }
    }

    fun getAllAccounts() {
        viewModelScope.launch {
            _allAccounts.value =
                moneyboxDao.getAllAccounts()
        }
    }

    fun getAccountsBalance() {
        viewModelScope.launch {
            _accountsBalance.value = moneyboxDao.getAccountsBalance()
        }
    }

    suspend fun getAccountBalance(accountId: Long): Double {
        return moneyboxDao.getAccountBalance(accountId)
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
