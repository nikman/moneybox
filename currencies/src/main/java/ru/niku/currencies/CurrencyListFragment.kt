package ru.niku.currencies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.niku.coreapi.MoneyboxApp
import ru.niku.coreapi.dto.CurrencyModel
import ru.niku.currencies.databinding.FragmentCurrencyListBinding
import ru.niku.currencies.di.CurrenciesComponent
import javax.inject.Inject

class CurrencyListFragment : Fragment() {

    private lateinit var currencyRecyclerView: RecyclerView // di!
    private var adapter: CurrencyAdapter = CurrencyAdapter(emptyList())

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    //private val viewModel by activityViewModels<ReportsViewModel>()
    private val viewModel: CurrencyListViewModel by viewModels {
        viewModelFactory
    }

    private var _binding: FragmentCurrencyListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
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
        /*val notificationsViewModel =
            ViewModelProvider(this).get(CurrenciesViewModel::class.java)*/

        _binding = FragmentCurrencyListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //val recyclerView: RecyclerView = binding.recyclerView
        currencyRecyclerView = binding.recyclerView
        currencyRecyclerView.layoutManager = LinearLayoutManager(context)
        currencyRecyclerView.adapter = adapter

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

        //Log.d(TAG, "updateUI cur size=${currencies.size}")

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
            titleTextView.text = this.currency.toString()
            codeTextView.text = this.currency.toString()

            //val uuidAsString = getStoredCurrencyId(requireContext())

            /*if (uuidAsString != null) {
                if (uuidAsString.isNotEmpty() &&
                    currency.currency_id == UUID.fromString(uuidAsString)) {
                    val boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD)
                    titleTextView.typeface = boldTypeface
                    codeTextView.typeface = boldTypeface
                }
            }*/
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
            //Log.d(TAG, "currencies Size: $currenciesSize")
            return currenciesSize
        }

        override fun onBindViewHolder(holder: CurrencyHolder, position: Int) {
            val currency = currencies[position]
            //holder.apply { titleTextView.text = account.title }
            //Log.d(TAG, "Position: $position")
            holder.bind(currency)
        }

    }

}