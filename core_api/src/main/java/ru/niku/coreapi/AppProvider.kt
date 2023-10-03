package ru.niku.coreapi

import android.content.Context

interface AppProvider {
    fun provideContext(): Context
}