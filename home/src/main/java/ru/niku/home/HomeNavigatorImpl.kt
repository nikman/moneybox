package ru.niku.home

import androidx.fragment.app.FragmentManager
import ru.niku.coreapi.HomeNavigator
import ru.niku.home.home.HomeFragment
import javax.inject.Inject

class HomeNavigatorImpl @Inject constructor() : HomeNavigator {

    override fun startHomeScreen(containerId: Int, fragmentManager: FragmentManager) {
        fragmentManager.beginTransaction()
            .replace(containerId, HomeFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }
}