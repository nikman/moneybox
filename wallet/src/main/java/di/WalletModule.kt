package di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import wallet.WalletViewModelFactory

@Module
interface WalletModule {
    @Binds
    fun bindsWalletViewModelFactory(walletViewModelFactory: WalletViewModelFactory): ViewModelProvider.Factory
}