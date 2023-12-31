package ru.niku.reports_api

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager

interface ReportsNavigator {
    fun startReportsScreen(@IdRes containerId: Int, fragmentManager: FragmentManager)
}