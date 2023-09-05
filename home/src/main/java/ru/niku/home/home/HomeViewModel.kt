package ru.niku.home.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.niku.coreapi.MoneyboxDao
import javax.inject.Inject

class HomeViewModel constructor(private val moneyboxDao: MoneyboxDao) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}

@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory @Inject constructor(
    private val moneyboxDao: MoneyboxDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(moneyboxDao) as T
    }
}
