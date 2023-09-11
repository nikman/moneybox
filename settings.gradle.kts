pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "moneybox"
include(":app")
include(":core")
include(":core_api")
include(":core_impl")
include(":home")
include(":main")
include(":create_account_api")
include(":create_account")
include(":reports")
include(":wallet")
include(":currencies")
include(":network")
include(":network_api")
include(":network_impl")
include(":money_transaction")
include(":money_transaction_api")
