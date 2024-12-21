package ru.niku.coreapi.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import ru.niku.coreapi.dto.Currency
//import ru.niku.coreapi.dto.CurrencyListItem

interface WebApi {

    /*@GET("latest/currencies.json")
    suspend fun getCurrenciesList() : Response<List<CurrencyListItem>>*/

    @GET("pair/{currencyCode}/RUB")
    suspend fun getCurrencyValue(@Path("currencyCode") currencyCode : String) : Response<Currency>

}