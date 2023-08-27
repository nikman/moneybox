package ru.niku.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.niku.coreapi.MoneyboxDao
import javax.inject.Inject

class HomeViewModel constructor(private val moneyboxDao: MoneyboxDao) :ViewModel() {

}

class HomeViewModelFactory @Inject constructor(
    private val moneyboxDao: MoneyboxDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(moneyboxDao) as T
    }
}