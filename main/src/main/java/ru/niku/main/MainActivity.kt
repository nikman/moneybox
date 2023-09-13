package ru.niku.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ru.niku.coreapi.CurrenciesNavigator
import ru.niku.coreapi.HomeNavigator
import ru.niku.coreapi.MoneyboxApp
import ru.niku.coreapi.ReportsNavigator
import ru.niku.create_account_api.CreateAccountMediator
import ru.niku.main.databinding.ActivityMainBinding
import ru.niku.main.di.MainComponent
import ru.niku.money_transaction_api.MoneyTransactionMediator
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var homeNavigator: HomeNavigator

    @Inject
    lateinit var reportsNavigator: ReportsNavigator

    @Inject
    lateinit var currenciesNavigator: CurrenciesNavigator

    @Inject
    lateinit var createAccountMediator: CreateAccountMediator

    @Inject
    lateinit var moneyTransactionMediator: MoneyTransactionMediator

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MainComponent.create((application as MoneyboxApp).getFacade()).inject(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mainNavigationView = binding.navView

        homeNavigator.startHomeScreen(
            R.id.mainFragmentsContainer,
            supportFragmentManager
        )

        val fab = binding.fab

        val createAccountListener: (View) -> (Unit) = {
            createAccountMediator.openCreateAccountScreen(
                R.id.mainFragmentsContainer,
                supportFragmentManager)
        }
        val openMoneyTransactionScreenListener: (View) -> (Unit) = {
            moneyTransactionMediator.openMoneyTransactionScreen(
                R.id.mainFragmentsContainer,
                supportFragmentManager)
        }

        fab.setOnClickListener(createAccountListener)

        mainNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    homeNavigator.startHomeScreen(
                        R.id.mainFragmentsContainer,
                        supportFragmentManager
                    )
                    fab.setImageResource(R.drawable.baseline_add_card_24)
                    fab.setOnClickListener(createAccountListener)
                    true
                }
                R.id.navigation_reports -> {
                    reportsNavigator.startReportsScreen(
                        R.id.mainFragmentsContainer,
                        supportFragmentManager
                    )
                    fab.setImageResource(R.drawable.baseline_post_add_24)
                    fab.setOnClickListener(openMoneyTransactionScreenListener)
                    true
                }
                R.id.navigation_currencies -> {
                    currenciesNavigator.startCurrenciesScreen(
                        R.id.mainFragmentsContainer,
                        supportFragmentManager
                    )
                    fab.setImageResource(R.drawable.baseline_playlist_add_24)
                    true
                }
                else -> false
            }
        }
    }

}