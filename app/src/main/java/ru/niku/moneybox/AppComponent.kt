package ru.niku.moneybox

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.niku.coreapi.AppProvider
import javax.inject.Singleton

@Singleton
@Component(
    //modules = [AppModule::class, NetworkModule::class]
    modules = [AppModule::class]
)
interface AppComponent : AppProvider {

    companion object {

        private var appComponent: AppProvider? = null

        fun create(application: Application): AppProvider {
            return appComponent ?: DaggerAppComponent
                .builder()
                .application(application.applicationContext)
                .build().also { appComponent = it }
        }
    }

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(context: Context): Builder

        fun build(): AppComponent
    }
}