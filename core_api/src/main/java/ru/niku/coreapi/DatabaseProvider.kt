package ru.niku.coreapi

interface DatabaseProvider {

    fun provideDatabase(): MoneyboxDatabaseContract

    fun moneyboxDao(): MoneyboxDao

}