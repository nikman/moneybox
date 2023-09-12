package ru.niku.coreapi

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager

interface ReportsNavigator {
    fun startReportsScreen(@IdRes containerId: Int, fragmentManager: FragmentManager)
}