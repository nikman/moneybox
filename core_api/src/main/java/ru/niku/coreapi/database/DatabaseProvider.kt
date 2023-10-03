package ru.niku.coreapi.database

interface DatabaseProvider {
    fun provideDatabase(): MoneyboxDatabaseContract
    fun moneyboxDao(): MoneyboxDao
}