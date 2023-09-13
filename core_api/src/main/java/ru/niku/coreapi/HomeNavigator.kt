package ru.niku.coreapi

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager

interface HomeNavigator {
    fun startHomeScreen(@IdRes containerId: Int, fragmentManager: FragmentManager)
}