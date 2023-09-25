package ru.niku.create_currency

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import ru.niku.coreapi.MoneyboxApp
import ru.niku.coreapi.dto.Currency
import ru.niku.create_currency.databinding.FragmentCreateCurrencyBinding
import ru.niku.create_currency.di.CreateCurrencyComponent
import javax.inject.Inject

class CreateCurrencyFragment: Fragment() {

    private val currency: Currency = Currency()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: CreateCurrencyViewModel by viewModels {
        viewModelFactory
    }

    private var _binding: FragmentCreateCurrencyBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CreateCurrencyComponent.create((requireActivity().application as MoneyboxApp).getFacade())
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCreateCurrencyBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val titleField = binding.currencyTitle
        val titleFieldWatcher = object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val changedText = s.toString()
                currency.name = changedText
            }

            override fun afterTextChanged(s: Editable?) {

            }

        }
        titleField.addTextChangedListener(titleFieldWatcher)

        val codeField = binding.currencyCode
        val codeFieldWatcher = object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val changedText = s.toString()
                currency.code = changedText
            }

            override fun afterTextChanged(s: Editable?) {

            }

        }
        codeField.addTextChangedListener(codeFieldWatcher)

        val saveButton = binding.currencySaveButtom
        saveButton.apply {
            setOnClickListener {
                saveEntity()
                activity?.supportFragmentManager?.popBackStack()
            }
        }

        return root
    }

    private fun saveEntity() {
        viewModel.addCurrency(currency)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(): Fragment {
            return CreateCurrencyFragment()
        }
    }

}