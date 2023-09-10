package ru.niku.create_account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.niku.coreapi.MoneyboxDao
import ru.niku.coreapi.dto.Account
import javax.inject.Inject

class CreateAccountViewModel constructor(private val moneyboxDao: MoneyboxDao): ViewModel() {

    fun addAccount(account: Account) {
        viewModelScope.launch {
            moneyboxDao.addAccount(account)
        }
    }

}

@Suppress("UNCHECKED_CAST")
class CreateAccountViewModelFactory @Inject constructor(
    private val moneyboxDao: MoneyboxDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CreateAccountViewModel(moneyboxDao) as T
    }
}