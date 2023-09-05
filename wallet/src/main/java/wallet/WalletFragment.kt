package wallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import di.WalletComponent
import ru.niku.coreapi.MoneyboxApp
import ru.niku.wallet.databinding.FragmentWalletBinding
import javax.inject.Inject


class WalletFragment: Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<WalletViewModel>()

    private var _binding: FragmentWalletBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WalletComponent.create((requireActivity().application as MoneyboxApp).getFacade())
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        /*val walletViewModel =
            ViewModelProvider(this).get(WalletViewModel::class.java)*/

        _binding = FragmentWalletBinding.inflate(inflater, container, false)
        val root: View = binding.root

        /*val textView: TextView = binding.textNotifications
        viewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(): Fragment {
            return WalletFragment()
        }
    }

}