package ru.niku.reports.navigation

import androidx.fragment.app.FragmentManager
import ru.niku.reports.reports.ReportsFragment
import javax.inject.Inject

class ReportsNavigatorImpl @Inject constructor() : ru.niku.reports_api.ReportsNavigator {
    override fun startReportsScreen(containerId: Int, fragmentManager: FragmentManager) {
        fragmentManager.beginTransaction()
            .replace(containerId, ReportsFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }
}