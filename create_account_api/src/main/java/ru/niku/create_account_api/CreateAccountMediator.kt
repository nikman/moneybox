package ru.niku.create_account_api

import androidx.fragment.app.FragmentManager

interface CreateAccountMediator {

    fun openCreateAccountScreen(containerId: Int, fragmentManager: FragmentManager)
}