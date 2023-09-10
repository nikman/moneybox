package ru.niku.create_account

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
import ru.niku.coreapi.dto.Account
import ru.niku.create_account.databinding.FragmentCreateAccountBinding
import ru.niku.create_account.di.CreateAccountComponent
import javax.inject.Inject

class CreateAccountFragment: Fragment() {

    private lateinit var account: Account

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: CreateAccountViewModel by viewModels {
        viewModelFactory
    }

    private var _binding: FragmentCreateAccountBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CreateAccountComponent.create((requireActivity().application as MoneyboxApp).getFacade())
            .inject(this)
        account = Account()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCreateAccountBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val titleField = binding.accountTitle
        val titleFieldWatcher = object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val changedText = s.toString()
                account.title = changedText
            }

            override fun afterTextChanged(s: Editable?) {

            }

        }
        titleField.addTextChangedListener(titleFieldWatcher)

        val noteField = binding.accountNote
        val noteFieldWatcher = object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val changedText = s.toString()
                account.note = changedText
            }

            override fun afterTextChanged(s: Editable?) {

            }

        }
        noteField.addTextChangedListener(noteFieldWatcher)

        val saveButton = binding.accountSaveButtom
        saveButton.apply {
            setOnClickListener {
                saveEntity()
            }
        }

        return root
    }

    fun saveEntity() {
        viewModel.addAccount(account = this.account)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(): Fragment {
            return CreateAccountFragment()
        }
    }

}