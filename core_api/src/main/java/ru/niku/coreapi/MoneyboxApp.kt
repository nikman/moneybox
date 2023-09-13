package ru.niku.coreapi

import ru.niku.coreapi.network.NetworkProvider

interface MoneyboxApp {

    fun getFacade(): ProvidersFacade

    fun getNetwork(): NetworkProvider

}