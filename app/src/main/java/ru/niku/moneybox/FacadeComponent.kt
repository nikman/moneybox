package ru.niku.moneybox

import android.app.Application
import dagger.Component
import ru.niku.core.CoreProviders
import ru.niku.coreapi.AppProvider
import ru.niku.coreapi.DatabaseProvider
import ru.niku.coreapi.ProvidersFacade
import ru.niku.create_account.CreateAccountExternalModule

@Component(
    dependencies = [AppProvider::class, DatabaseProvider::class],
    modules = [MediatorsBindings::class, CreateAccountExternalModule::class]
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