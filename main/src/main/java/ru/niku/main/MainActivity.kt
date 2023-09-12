package ru.niku.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.niku.coreapi.CurrenciesNavigator
import ru.niku.coreapi.HomeNavigator
import ru.niku.coreapi.MoneyboxApp
import ru.niku.coreapi.ReportsNavigator
import ru.niku.create_account_api.CreateAccountMediator
import ru.niku.main.databinding.ActivityMainBinding
import ru.niku.main.di.MainComponent
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
        fab.apply {
            setOnClickListener {
                createAccountMediator.openCreateAccountScreen(
                    R.id.mainFragmentsContainer,
                    supportFragmentManager)
            }
        }

        mainNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    homeNavigator.startHomeScreen(
                        R.id.mainFragmentsContainer,
                        supportFragmentManager
                    )
                    fab.setImageResource(R.drawable.baseline_add_card_24)
                    true
                }
                R.id.navigation_reports -> {
                    reportsNavigator.startReportsScreen(
                        R.id.mainFragmentsContainer,
                        supportFragmentManager
                    )
                    fab.setImageResource(R.drawable.baseline_post_add_24)
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