package ru.niku.currencies

import androidx.fragment.app.FragmentManager
import ru.niku.coreapi.CurrenciesMediator
import javax.inject.Inject

class CurrenciesMediatorImpl @Inject constructor() : CurrenciesMediator {
    override fun startCurrenciesScreen(containerId: Int, fragmentManager: FragmentManager) {
        fragmentManager.beginTransaction()
            .replace(containerId, CurrencyListFragment.newInstance())
            .commit()
    }
}