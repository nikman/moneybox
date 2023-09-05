package di

import dagger.Component
import ru.niku.coreapi.ProvidersFacade
import wallet.WalletFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [WalletModule::class],
    dependencies = [ProvidersFacade::class]
)
interface WalletComponent {

    companion object {

        fun create(providersFacade: ProvidersFacade): WalletComponent {
            return DaggerWalletComponent
                .builder()
                .providersFacade(providersFacade)
                .build()
        }
    }

    fun inject(walletFragment: WalletFragment)

}