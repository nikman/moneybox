package ru.niku.reports

import androidx.fragment.app.FragmentManager
import ru.niku.coreapi.ReportsMediator
import ru.niku.reports.reports.ReportsFragment
import javax.inject.Inject

class ReportsMediatorImpl @Inject constructor() : ReportsMediator {
    override fun startReportsScreen(containerId: Int, fragmentManager: FragmentManager) {
        fragmentManager.beginTransaction()
            .add(containerId, ReportsFragment.newInstance())
            .commit()
    }
}