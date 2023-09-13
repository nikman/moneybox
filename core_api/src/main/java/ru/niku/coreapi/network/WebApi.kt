package ru.niku.coreapi.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import ru.niku.coreapi.dto.Currency
import ru.niku.coreapi.dto.CurrencyListItem

interface WebApi {

    @GET("currency-api@1/latest/currencies.json")
    suspend fun getCurrenciesList() : Response<List<CurrencyListItem>>

    @GET("currency-api@1/latest/currencies/{currencyCode}/rub.json")
    suspend fun getCurrencyValue(@Path("currencyCode") currencyCode : String) : Response<Currency>

}