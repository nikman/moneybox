package ru.niku.coreapi

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager

interface CurrenciesNavigator {
    fun startCurrenciesScreen(@IdRes containerId: Int, fragmentManager: FragmentManager)
}