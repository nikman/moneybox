package ru.niku.moneybox

import android.app.Application
import ru.niku.coreapi.MoneyboxApp
import ru.niku.coreapi.ProvidersFacade

class App: Application(), MoneyboxApp {

    companion object {
        private var facadeComponent: FacadeComponent? = null
    }

    override fun onCreate() {
        super.onCreate()
        getFacade()
    }

    override fun getFacade(): ProvidersFacade {
        return facadeComponent ?: FacadeComponent.init(this).also {
            facadeComponent = it
        }
    }
}