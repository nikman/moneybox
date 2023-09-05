package ru.niku.coreapi.dto

import ru.niku.coreapi.WebApi
import javax.inject.Inject

class MainRemoteData @Inject constructor(private val webApi : WebApi) {

    suspend fun getCurrencyValue(currencyCode : String) =
        webApi.getCurrencyValue(currencyCode = currencyCode)

}