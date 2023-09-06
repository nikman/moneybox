package ru.niku.coreapi

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager

interface WalletMediator {
    fun startWalletScreen(@IdRes containerId: Int, fragmentManager: FragmentManager)
}