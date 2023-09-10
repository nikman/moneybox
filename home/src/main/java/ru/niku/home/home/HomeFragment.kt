package ru.niku.home.home

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
import ru.niku.coreapi.dto.Account
import ru.niku.home.R
import ru.niku.home.databinding.FragmentHomeBinding
import ru.niku.home.di.HomeComponent
import javax.inject.Inject

class HomeFragment : Fragment() {

    private lateinit var accountsRecyclerView: RecyclerView
    private var adapter: AccountsAdapter = AccountsAdapter(emptyList())
    //private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: HomeViewModel by viewModels {
        viewModelFactory
    }

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        HomeComponent.create((requireActivity().application as MoneyboxApp).getFacade())
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewModel.getOverallBalance()

        val cardView = binding.cardCommonBalance
        //val imageView = binding.imageView
        val textView = binding.textView

        cardView.visibility = View.VISIBLE
        textView.text = "0"

        viewModel.text.observe(viewLifecycleOwner) {
                balance -> textView.text = balance
        }

        accountsRecyclerView = binding.recyclerView
        accountsRecyclerView.layoutManager = LinearLayoutManager(context)
        accountsRecyclerView.adapter = adapter

        //swipeRefreshLayout = binding.accountsSwipeLayout

        /*swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            currencyRecyclerView.adapter = adapter
        }*/

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.allAccounts.observe(viewLifecycleOwner) {
                accounts -> accounts?.let { updateUI(accounts) }
        }

        viewModel.getAllAccounts()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updateUI(accounts: List<Account>) {

        adapter = AccountsAdapter(accounts)
        accountsRecyclerView.adapter = adapter

    }

    private inner class AccountHolder(view: View):
        RecyclerView.ViewHolder(view), View.OnClickListener {

        private lateinit var account: Account

        fun getItem() = account

        private val titleTextView: TextView = itemView.findViewById(R.id.account_title)
        private val noteTextView: TextView = itemView.findViewById(R.id.account_note)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            //callbacks?.onAccountSelected(account.account_id)
        }

        fun bind(account: Account) {

            this.account = account
            titleTextView.text = this.account.title
            noteTextView.text = this.account.note

            /*viewLifecycleOwner.lifecycleScope.launch {
                val b = viewModel.getAccountBalance(account.account_id)
                balanceTextView.text = b.....toString()
            }*/

        }
    }

    private inner class AccountsAdapter(var accounts: List<Account>):
        RecyclerView.Adapter<AccountHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountHolder {
            val itemView =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_item_view_account, parent, false)

            return AccountHolder(itemView)
        }

        override fun getItemCount() : Int {
            return accounts.size
        }

        override fun onBindViewHolder(holder: AccountHolder, position: Int) {
            holder.bind(accounts[position])
        }

    }

    companion object {
        fun newInstance(): Fragment {
            return HomeFragment()
        }
    }
}