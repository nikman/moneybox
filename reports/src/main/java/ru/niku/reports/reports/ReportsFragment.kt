package ru.niku.reports.reports

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import ru.niku.coreapi.MoneyboxApp
import ru.niku.reports.databinding.FragmentReportsBinding
import ru.niku.reports.di.ReportsComponent
import javax.inject.Inject

class ReportsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    //private val viewModel by activityViewModels<ReportsViewModel>()
    private val viewModel: ReportsViewModel by viewModels {
        viewModelFactory
    }

    /*private val viewModel: ReportsViewModel by viewModels {
        ReportsViewModel.create((application as MoneyboxApp).getFacade().moneyboxDao(), this)
    }*/

    private var _binding: FragmentReportsBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ReportsComponent.create((requireActivity().application as MoneyboxApp).getFacade())
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentReportsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textReports
        viewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        //textView.text = "reports"
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(): Fragment {
            return ReportsFragment()
        }
    }
}