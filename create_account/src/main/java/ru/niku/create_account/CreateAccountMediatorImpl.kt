package ru.niku.create_account

import androidx.fragment.app.FragmentManager
import ru.niku.create_account_api.CreateAccountMediator
import javax.inject.Inject


class CreateAccountMediatorImpl
@Inject constructor() : CreateAccountMediator {

    override fun openCreateAccountScreen(containerId: Int, fragmentManager: FragmentManager) {
        fragmentManager.beginTransaction()
            .replace(containerId, CreateAccountFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }
}