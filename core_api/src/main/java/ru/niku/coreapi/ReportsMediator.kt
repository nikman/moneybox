package ru.niku.coreapi

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager

interface ReportsMediator {
    fun startReportsScreen(@IdRes containerId: Int, fragmentManager: FragmentManager)
}