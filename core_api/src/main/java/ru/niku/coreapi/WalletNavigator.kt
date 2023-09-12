package ru.niku.coreapi

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager

interface WalletNavigator {
    fun startWalletScreen(@IdRes containerId: Int, fragmentManager: FragmentManager)
}