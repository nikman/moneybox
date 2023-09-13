package ru.niku.money_transaction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import ru.niku.coreapi.MoneyboxApp
import ru.niku.coreapi.dto.MoneyTransaction
import ru.niku.money_transaction.databinding.FragmentTransactionBinding
import ru.niku.money_transaction.di.MoneyTransactionComponent
import javax.inject.Inject

class MoneyTransactionFragment: Fragment() {

    private val transaction: MoneyTransaction = MoneyTransaction()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: MoneyTransactionViewModel by viewModels {
        viewModelFactory
    }

    private var _binding: FragmentTransactionBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MoneyTransactionComponent.create((requireActivity().application as MoneyboxApp).getFacade())
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentTransactionBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val saveButton = binding.transactionSaveButtom
        saveButton.apply {
            setOnClickListener {
                saveEntity()
                activity?.supportFragmentManager?.popBackStack();
            }
        }

        return root

    }

    private fun saveEntity() {
        viewModel.addTransaction(transaction = this.transaction)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(): Fragment {
            return MoneyTransactionFragment()
        }
    }

}