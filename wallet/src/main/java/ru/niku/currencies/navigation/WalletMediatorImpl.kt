package ru.niku.currencies.navigation

import androidx.fragment.app.FragmentManager
import ru.niku.coreapi.WalletMediator
import wallet.WalletFragment
import javax.inject.Inject

class WalletMediatorImpl @Inject constructor(): WalletMediator {
    override fun startWalletScreen(containerId: Int, fragmentManager: FragmentManager) {
        fragmentManager.beginTransaction()
            .add(containerId, WalletFragment.newInstance())
            .commit()
    }
}