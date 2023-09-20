package ru.niku.reports.reports

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.niku.coreapi.MoneyboxApp
import ru.niku.coreapi.TransactionType
import ru.niku.coreapi.dto.ExpencesByCategory
import ru.niku.coreapi.dto.MoneyTransactionWithProperties
import ru.niku.reports.R
import ru.niku.reports.databinding.FragmentReportsBinding
import ru.niku.reports.di.ReportsComponent
import ru.niku.uikit.PayLoadModel
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject
import kotlin.math.abs

class ReportsFragment : Fragment() {

    private lateinit var transactionsRecyclerView: RecyclerView
    private var adapter: TransactionsAdapter = TransactionsAdapter(emptyList())

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: ReportsViewModel by viewModels {
        viewModelFactory
    }

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

        transactionsRecyclerView = binding.recyclerView
        transactionsRecyclerView.layoutManager = LinearLayoutManager(context)
        transactionsRecyclerView.adapter = adapter

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.topTransactions.observe(viewLifecycleOwner) {
                transactions -> transactions?.let { updateUI(transactions) }
        }

        viewModel.getTopTransactions()

        viewModel.expencesByCategory.observe(viewLifecycleOwner) {
                expences -> expences?.let { updateReport(expences) }
        }

        viewModel.getExpencesByCategory()
    }

    private fun updateUI(transactions: List<MoneyTransactionWithProperties>) {

        adapter = TransactionsAdapter(transactions)
        transactionsRecyclerView.adapter = adapter

    }

    private fun updateReport(expences: List<ExpencesByCategory>) {

        val pieChart = binding.pieChartView

        val categoryList = expences.map {
            PayLoadModel(0, "", abs(it.amount), it.category)
        }

        val expencesList = categoryList.groupingBy { it.category }
            .reduce { _, acc, element ->
                PayLoadModel(0, "", acc.amount + element.amount, acc.category)
            }
            .values.toList()

        pieChart.setValues(expencesList)
        pieChart.visibility = View.VISIBLE
        pieChart.startAnimation()

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private inner class TransactionHolder(view: View):
        RecyclerView.ViewHolder(view), View.OnClickListener {

        private lateinit var transaction: MoneyTransactionWithProperties

        private val accountTextView: TextView = itemView.findViewById(R.id.account)
        private val dateTextView: TextView = itemView.findViewById(R.id.date)
        private val categoryTextView: TextView = itemView.findViewById(R.id.category)
        private val amountTextView: TextView = itemView.findViewById(R.id.amount)
        @ColorInt
        private var colorAccent = 0
        @ColorInt
        private var colorError = 0

        init {
            itemView.setOnClickListener(this)

            val typedValue = TypedValue()
            context?.theme?.resolveAttribute(android.R.attr.colorAccent, typedValue, true)
            colorAccent = typedValue.data
            context?.theme?.resolveAttribute(android.R.attr.colorError, typedValue, true)
            colorError = typedValue.data
        }

        override fun onClick(v: View?) {
            //callbacks?.onAccountSelected(account.account_id)
        }

        fun bind(transaction: MoneyTransactionWithProperties) {

            this.transaction = transaction
            accountTextView.text = this.transaction.accountSource.toString()
            dateTextView.text =
                SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(this.transaction.transaction.date)
            amountTextView.text = this.transaction.transaction.amount.toString()
            categoryTextView.text = this.transaction.transaction.category

            when (this.transaction.transaction.ttype) {
                TransactionType.EXPENCE -> amountTextView.setTextColor(colorError)
                TransactionType.INCOME -> amountTextView.setTextColor(colorAccent)
                TransactionType.TRANSFER -> amountTextView.setTextColor(Color.BLUE)
            }

        }
    }

    private inner class TransactionsAdapter(var transactions: List<MoneyTransactionWithProperties>):
        RecyclerView.Adapter<TransactionHolder>() {

        @RequiresApi(Build.VERSION_CODES.O)
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionHolder {
            val itemView =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_item_view_transaction, parent, false)

            return TransactionHolder(itemView)
        }

        override fun getItemCount() : Int {
            return transactions.size
        }

        @RequiresApi(Build.VERSION_CODES.O)
        override fun onBindViewHolder(holder: TransactionHolder, position: Int) {
            holder.bind(transactions[position])
        }

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