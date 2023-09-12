package ru.niku.currencies

import androidx.fragment.app.FragmentManager
import ru.niku.coreapi.CurrenciesNavigator
import javax.inject.Inject

class CurrenciesNavigatorImpl @Inject constructor() : CurrenciesNavigator {
    override fun startCurrenciesScreen(containerId: Int, fragmentManager: FragmentManager) {
        fragmentManager.beginTransaction()
            .replace(containerId, CurrencyListFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }
}