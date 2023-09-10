package ru.niku.currencies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.coroutines.launch
import ru.niku.coreapi.MoneyboxApp
import ru.niku.coreapi.dto.CurrencyModel
import ru.niku.currencies.databinding.FragmentCurrencyListBinding
import ru.niku.currencies.di.CurrenciesComponent
import javax.inject.Inject

class CurrencyListFragment : Fragment() {

    private lateinit var currencyRecyclerView: RecyclerView
    private var adapter: CurrencyAdapter = CurrencyAdapter(emptyList())
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: CurrencyListViewModel by viewModels {
        viewModelFactory
    }

    private var _binding: FragmentCurrencyListBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CurrenciesComponent.create((requireActivity().application as MoneyboxApp).getFacade())
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCurrencyListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        currencyRecyclerView = binding.recyclerView
        currencyRecyclerView.layoutManager = LinearLayoutManager(context)
        currencyRecyclerView.adapter = adapter

        swipeRefreshLayout = binding.currencySwipeLayout

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            currencyRecyclerView.adapter = adapter
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.allCurrencies.observe(viewLifecycleOwner) {
                currencies -> currencies?.let { updateUI(currencies) }
        }

        viewModel.getAllCurrencies()

    }

    private fun updateUI(currencies: List<CurrencyModel>) {

        adapter = CurrencyAdapter(currencies)
        currencyRecyclerView.adapter = adapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(): Fragment {
            return CurrencyListFragment()
        }
    }

    private inner class CurrencyHolder(view: View):
        RecyclerView.ViewHolder(view), View.OnClickListener {

        private lateinit var currency: CurrencyModel

        fun getItem() = currency

        private val titleTextView: TextView = itemView.findViewById(R.id.currency_title)
        private val codeTextView: TextView = itemView.findViewById(R.id.currency_code)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            //callbacks?.onCurrencySelected(currency.currency_id)
        }

        fun bind(currency: CurrencyModel) {

            this.currency = currency
            titleTextView.text = this.currency.name
            codeTextView.text = this.currency.code

            viewLifecycleOwner.lifecycleScope.launch {
                val b = viewModel.getCurrencyValue(currency.code)
                codeTextView.text = b.body()?.rub.toString()
            }

        }
    }

    private inner class CurrencyAdapter(var currencies: List<CurrencyModel>):
        RecyclerView.Adapter<CurrencyHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyHolder {
            val itemView =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_item_view_currency, parent, false)

            return CurrencyHolder(itemView)
        }

        override fun getItemCount() : Int {
            val currenciesSize = currencies.size
            return currenciesSize
        }

        override fun onBindViewHolder(holder: CurrencyHolder, position: Int) {
            val currency = currencies[position]
            holder.bind(currency)
        }

    }

}