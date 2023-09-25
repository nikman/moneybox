package ru.niku.create_currency_api

import androidx.fragment.app.FragmentManager

interface CreateCurrencyMediator {

    fun openCreateCurrencyScreen(containerId: Int, fragmentManager: FragmentManager)
}