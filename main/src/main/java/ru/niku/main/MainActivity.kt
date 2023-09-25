package ru.niku.main

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.niku.coreapi.CurrenciesNavigator
import ru.niku.coreapi.HomeNavigator
import ru.niku.coreapi.MoneyboxApp
import ru.niku.reports_api.ReportsNavigator
import ru.niku.create_account_api.CreateAccountMediator
import ru.niku.create_currency_api.CreateCurrencyMediator
import ru.niku.main.databinding.ActivityMainBinding
import ru.niku.main.di.MainComponent
import ru.niku.money_transaction_api.MoneyTransactionMediator
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val SAVED_STATE_ID = "mainNavViewId"

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

    @Inject
    lateinit var createCurrencyMediator: CreateCurrencyMediator

    private lateinit var binding: ActivityMainBinding

    private val createAccountListener: (View) -> (Unit) = {
        createAccountMediator.openCreateAccountScreen(
            R.id.mainFragmentsContainer,
            supportFragmentManager)
        //fab.visibility = View.GONE
    }
    private val openMoneyTransactionScreenListener: (View) -> (Unit) = {
        moneyTransactionMediator.openMoneyTransactionScreen(
            R.id.mainFragmentsContainer,
            supportFragmentManager)
        //fab.visibility = View.GONE
    }
    private val openCreateCurrencyScreenListener: (View) -> (Unit) = {
        createCurrencyMediator.openCreateCurrencyScreen(
            R.id.mainFragmentsContainer,
            supportFragmentManager)
        //fab.visibility = View.GONE
    }

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
        val ab: ActionBar? = supportActionBar
        ab?.title = applicationContext.getString(R.string.main_screen_title)

        fab.setOnClickListener(createAccountListener)

        mainNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    navigateToHomeScreen(ab, fab, createAccountListener)
                    true
                }
                R.id.navigation_reports -> {
                    navigateToReportsScreen(ab, fab, openMoneyTransactionScreenListener)
                    true
                }
                R.id.navigation_currencies -> {
                    navigateToCurresciesScreen(ab, fab, openCreateCurrencyScreenListener)
                    true
                }
                else -> false
            }
        }
    }

    private fun navigateToCurresciesScreen(
        ab: ActionBar?,
        fab: FloatingActionButton,
        openCreateCurrencyScreenListener: (View) -> Unit
    ) {
        currenciesNavigator.startCurrenciesScreen(
            R.id.mainFragmentsContainer,
            supportFragmentManager
        )
        ab?.title = applicationContext.getString(R.string.currencies_screen_title)
        fab.setImageResource(R.drawable.baseline_playlist_add_24)
        fab.setOnClickListener(openCreateCurrencyScreenListener)
    }

    private fun navigateToReportsScreen(
        ab: ActionBar?,
        fab: FloatingActionButton,
        openMoneyTransactionScreenListener: (View) -> Unit
    ) {
        reportsNavigator.startReportsScreen(
            R.id.mainFragmentsContainer,
            supportFragmentManager
        )
        ab?.title = applicationContext.getString(R.string.reports_screen_title)
        fab.setImageResource(R.drawable.baseline_post_add_24)
        fab.setOnClickListener(openMoneyTransactionScreenListener)
    }

    private fun navigateToHomeScreen(
        ab: ActionBar?,
        fab: FloatingActionButton,
        createAccountListener: (View) -> Unit
    ) {
        homeNavigator.startHomeScreen(
            R.id.mainFragmentsContainer,
            supportFragmentManager
        )
        ab?.title = applicationContext.getString(R.string.main_screen_title)
        fab.setImageResource(R.drawable.baseline_add_card_24)
        fab.setOnClickListener(createAccountListener)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val mainNavigationView = binding.navView
        outState.putInt(SAVED_STATE_ID, mainNavigationView.selectedItemId)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val mainNavViewId = savedInstanceState.getInt(SAVED_STATE_ID, R.id.navigation_home)
        val fab = binding.fab
        val ab: ActionBar? = supportActionBar

        when (mainNavViewId) {
            R.id.navigation_home -> {
                navigateToHomeScreen(ab, fab, createAccountListener)
            }
            R.id.navigation_reports -> {
                navigateToReportsScreen(ab, fab, openMoneyTransactionScreenListener)
            }
            R.id.navigation_currencies -> {
                navigateToCurresciesScreen(ab, fab, openCreateCurrencyScreenListener)
            }
        }

    }
}