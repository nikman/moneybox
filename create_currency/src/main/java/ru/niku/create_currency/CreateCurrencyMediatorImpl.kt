package ru.niku.create_currency

import androidx.fragment.app.FragmentManager
import ru.niku.create_currency_api.CreateCurrencyMediator
import javax.inject.Inject

class CreateCurrencyMediatorImpl
@Inject constructor() : CreateCurrencyMediator {

    override fun openCreateCurrencyScreen(containerId: Int, fragmentManager: FragmentManager) {
        fragmentManager.beginTransaction()
            .replace(containerId, CreateCurrencyFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }
}