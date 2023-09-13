package ru.niku.money_transaction_api

import androidx.fragment.app.FragmentManager

interface MoneyTransactionMediator {

    fun openMoneyTransactionScreen(containerId: Int, fragmentManager: FragmentManager)
}