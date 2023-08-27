package ru.niku.coreapi

interface DatabaseProvider {

    fun provideDatabase(): MoneyboxDatabaseContract

}