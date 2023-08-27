package ru.niku.create_account

import android.content.Context
import ru.niku.create_account_api.CreateAccountMediator
import javax.inject.Inject


class CreateAccountMediatorImpl
@Inject constructor() : CreateAccountMediator {

    override fun openCreateAccountScreen(context: Context) {
        //CreateHabitActivity.startCreateHabitActivity(context)
    }
}