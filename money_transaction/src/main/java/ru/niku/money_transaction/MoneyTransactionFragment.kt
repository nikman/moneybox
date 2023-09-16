package ru.niku.money_transaction

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.ListPopupWindow
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import ru.niku.coreapi.MoneyboxApp
import ru.niku.coreapi.TransactionType
import ru.niku.coreapi.dto.Account
import ru.niku.coreapi.dto.MoneyTransaction
import ru.niku.coreapi.dto.Turnovers
import ru.niku.money_transaction.databinding.FragmentTransactionBinding
import ru.niku.money_transaction.di.MoneyTransactionComponent
import java.text.DateFormat
import java.util.Calendar
import java.util.GregorianCalendar
import java.util.UUID
import javax.inject.Inject

class MoneyTransactionFragment: Fragment() {

    val uuid = UUID.randomUUID()

    private val transaction =
        MoneyTransaction(transactionUuid = uuid.toString(), date = Calendar.getInstance().time)
    private val turnover =
        Turnovers(transactionUuid = uuid.toString())

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
                activity?.supportFragmentManager?.popBackStack()
            }
        }

        val toggleButtonIncome = binding.buttonIncome
        toggleButtonIncome.apply {
            setOnClickListener {
                transaction.multiplier = 1
                turnover.multiplier = 1
                transaction.ttype = TransactionType.INCOME
                turnover.ttype = TransactionType.INCOME
            }
        }

        val toggleButtonExpence = binding.buttonExpence
        toggleButtonExpence.apply {
            setOnClickListener {
                transaction.multiplier = -1
                turnover.multiplier = -1
                transaction.ttype = TransactionType.EXPENCE
                turnover.ttype = TransactionType.EXPENCE
            }
        }

        val toggleButtonTransfer = binding.buttonTransfer
        toggleButtonTransfer.apply {
            setOnClickListener {
                transaction.multiplier = 0
                turnover.multiplier = 0
                transaction.ttype = TransactionType.TRANSFER
                turnover.ttype = TransactionType.TRANSFER
            }
        }

        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.allActiveAccounts.observe(viewLifecycleOwner) {
                accounts -> accounts?.let { updateAccountSource(accounts) }
        }
        viewModel.getAllActiveAccounts()
        populateCategories()
    }

    private fun updateAccountSource(accounts: List<Account>) {

        if (accounts.isEmpty()) {
            return
        }

        val accountsStrings =
            List(accounts.size) {
                    i -> accounts[i].toString()
            }

        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.list_item,
            accountsStrings)

        val maxWidth = requireContext().resources.displayMetrics.widthPixels - 20

        val listPopupAccountSourceButton = binding.accountSource
        val listPopupWindowAccountSource =
            ListPopupWindow(requireContext(), null, androidx.appcompat.R.attr.listPopupWindowStyle)
        listPopupWindowAccountSource.anchorView = listPopupAccountSourceButton
        listPopupWindowAccountSource.width = maxWidth

        listPopupAccountSourceButton.text = accounts[0].toString()
        transaction.account_id = accounts[0].account_id
        turnover.accountId = accounts[0].account_id

        listPopupWindowAccountSource.setAdapter(adapter)
        listPopupWindowAccountSource.setOnItemClickListener {
                parent: AdapterView<*>?, view: View?, position: Int, id: Long ->
            transaction.account_id = accounts[position].account_id
            turnover.accountId = accounts[position].account_id
            listPopupAccountSourceButton.text = accounts[position].toString()
            listPopupWindowAccountSource.dismiss()
        }

        listPopupAccountSourceButton.setOnClickListener {
                v: View? -> listPopupWindowAccountSource.show()
        }

    }

    private fun populateCategories() {

        val categoriesStrings = listOf(
            "Food",
            "Phone",
            "Education",
            "Children",
            "Salary"
        )

        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.list_item,
            categoriesStrings)

        val maxWidth = requireContext().resources.displayMetrics.widthPixels - 20

        val listPopupCategoriesButton = binding.category
        val listPopupWindowCategories =
            ListPopupWindow(requireContext(), null, androidx.appcompat.R.attr.listPopupWindowStyle)
        listPopupWindowCategories.anchorView = listPopupCategoriesButton
        listPopupWindowCategories.width = maxWidth

        listPopupCategoriesButton.text = categoriesStrings[0]
        transaction.category = categoriesStrings[0]
        turnover.category = categoriesStrings[0]

        listPopupWindowCategories.setAdapter(adapter)
        listPopupWindowCategories.setOnItemClickListener {
                parent: AdapterView<*>?, view: View?, position: Int, id: Long ->
            transaction.category = categoriesStrings[position]
            turnover.category = categoriesStrings[position]
            listPopupCategoriesButton.text = categoriesStrings[position]
            listPopupWindowCategories.dismiss()
        }

        listPopupCategoriesButton.setOnClickListener {
                v: View? -> listPopupWindowCategories.show()
        }

    }

    private fun saveEntity() {
        viewModel.addTransaction(transaction = this.transaction)
        viewModel.addTurnover(turnover = turnover)
    }

    override fun onStart() {
        super.onStart()
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dateButton = binding.date
        dateButton.setOnClickListener {

            val dpd = DatePickerDialog(requireContext(), {
                    _, year, monthOfYear, dayOfMonth ->
                val selectedDate = GregorianCalendar(year, monthOfYear, dayOfMonth).time
                dateButton.text =
                    DateFormat.getDateInstance().format(selectedDate)
                transaction.date = selectedDate
                turnover.date = selectedDate
            }, year, month, day)
            dpd.show()
        }
        dateButton.text =
            DateFormat.getDateInstance().format(transaction.date)

        val amountWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                transaction.amount = if (count > 0) s.toString().toDouble() * transaction.multiplier else 0.0
                turnover.amount = transaction.amount
                //transaction.amount_to = 0.0 //-moneyTransaction.amount_from
            }

            override fun afterTextChanged(s: Editable?) {  }
        }
        val amountField = binding.amount
        amountField.addTextChangedListener(amountWatcher)

        val noteField = binding.note
        val noteFieldWatcher = object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val changedText = s.toString()
                transaction.note = changedText
            }

            override fun afterTextChanged(s: Editable?) {

            }

        }
        noteField.addTextChangedListener(noteFieldWatcher)
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