package ru.niku.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.niku.coreapi.CurrenciesMediator
import ru.niku.coreapi.HomeMediator
import ru.niku.coreapi.MoneyboxApp
import ru.niku.coreapi.ReportsMediator
import ru.niku.create_account_api.CreateAccountMediator
import ru.niku.main.databinding.ActivityMainBinding
import ru.niku.main.di.MainComponent
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var homeMediator: HomeMediator

    @Inject
    lateinit var reportsMediator: ReportsMediator

    @Inject
    lateinit var currenciesMediator: CurrenciesMediator

    @Inject
    lateinit var createAccountMediator: CreateAccountMediator

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MainComponent.create((application as MoneyboxApp).getFacade()).inject(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mainNavigationView = binding.navView

        homeMediator.startHomeScreen(
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
                    homeMediator.startHomeScreen(
                        R.id.mainFragmentsContainer,
                        supportFragmentManager
                    )
                    true
                }
                R.id.navigation_reports -> {
                    reportsMediator.startReportsScreen(
                        R.id.mainFragmentsContainer,
                        supportFragmentManager
                    )
                    true
                }
                R.id.navigation_currencies -> {
                    currenciesMediator.startCurrenciesScreen(
                        R.id.mainFragmentsContainer,
                        supportFragmentManager
                    )
                    true
                }
                else -> false
            }
        }

        /*val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_reports, R.id.navigation_currencies
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)*/
    }
}