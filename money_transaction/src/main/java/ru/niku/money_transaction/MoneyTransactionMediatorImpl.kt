package ru.niku.money_transaction

import androidx.fragment.app.FragmentManager
import ru.niku.money_transaction_api.MoneyTransactionMediator
import javax.inject.Inject

class MoneyTransactionMediatorImpl
@Inject constructor() : MoneyTransactionMediator {

    override fun openMoneyTransactionScreen(containerId: Int, fragmentManager: FragmentManager) {
        fragmentManager.beginTransaction()
            .replace(containerId, MoneyTransactionFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }
}