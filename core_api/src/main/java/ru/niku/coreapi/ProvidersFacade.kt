package ru.niku.coreapi

import ru.niku.coreapi.database.DatabaseProvider

interface ProvidersFacade : MediatorsProvider, DatabaseProvider, AppProvider