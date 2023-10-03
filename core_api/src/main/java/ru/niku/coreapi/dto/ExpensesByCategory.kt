package ru.niku.coreapi.dto

data class ExpensesByCategory(
    val category: String = "",
    val amount: Double = 0.0
)
