package ru.niku.currencies.navigation

import androidx.fragment.app.FragmentManager
import ru.niku.coreapi.WalletNavigator
import wallet.WalletFragment
import javax.inject.Inject

class WalletNavigatorImpl @Inject constructor(): WalletNavigator {
    override fun startWalletScreen(containerId: Int, fragmentManager: FragmentManager) {
        fragmentManager.beginTransaction()
            .add(containerId, WalletFragment.newInstance())
            .commit()
    }
}