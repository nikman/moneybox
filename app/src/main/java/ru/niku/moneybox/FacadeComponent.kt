package ru.niku.moneybox

import android.app.Application
import dagger.Component
import ru.niku.core.CoreProviders
import ru.niku.coreapi.AppProvider
import ru.niku.coreapi.ProvidersFacade
import ru.niku.coreapi.database.DatabaseProvider
import ru.niku.create_account.CreateAccountNavigationModule
import ru.niku.money_transaction.MoneyTransactionNavigationModule

@Component(
    dependencies = [AppProvider::class, DatabaseProvider::class],
    modules = [MediatorsBindings::class, CreateAccountNavigationModule::class, MoneyTransactionNavigationModule::class]
)
interface FacadeComponent : ProvidersFacade {

    companion object {

         fun init(application: Application): FacadeComponent =
            DaggerFacadeComponent.builder()
                 .appProvider(AppComponent.create(application))
                 .databaseProvider(CoreProviders.createDatabaseBuilder(AppComponent.create(application)))
                 .build()
     }
}