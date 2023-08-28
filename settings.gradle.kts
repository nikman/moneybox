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
