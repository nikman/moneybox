package ru.niku.coreapi.network

interface NetworkProvider {

    fun provideWebApi(): WebApi

}