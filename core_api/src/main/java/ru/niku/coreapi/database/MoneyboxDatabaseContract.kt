package ru.niku.coreapi.database

import ru.niku.coreapi.database.MoneyboxDao

interface MoneyboxDatabaseContract {

    fun moneyboxDao(): MoneyboxDao

}