package ru.niku.home

import androidx.fragment.app.FragmentManager
import ru.niku.coreapi.HomeMediator
import ru.niku.home.home.HomeFragment
import javax.inject.Inject

class HomeMediatorImpl @Inject constructor() : HomeMediator {

    override fun startHomeScreen(containerId: Int, fragmentManager: FragmentManager) {
        fragmentManager.beginTransaction()
            .replace(containerId, HomeFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }
}