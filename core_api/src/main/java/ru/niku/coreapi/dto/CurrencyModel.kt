package ru.niku.coreapi.dto

data class CurrencyModel(
    val code: String, val name: String
) {
    override fun toString(): String {
        return "$name ($code)"
    }
}
