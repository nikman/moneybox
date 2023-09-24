plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-android")
}

android {
    namespace = "ru.niku.moneybox"
    compileSdk = 34

    defaultConfig {
        applicationId = "ru.niku.moneybox"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    
    implementation(project(mapOf("path" to ":core")))
    implementation(project(mapOf("path" to ":main")))
    implementation(project(mapOf("path" to ":home")))
    implementation(project(mapOf("path" to ":create_account")))
    implementation(project(mapOf("path" to ":create_account_api")))
    implementation(project(mapOf("path" to ":create_currency")))
    implementation(project(mapOf("path" to ":create_currency_api")))
    implementation(project(mapOf("path" to ":money_transaction")))
    implementation(project(mapOf("path" to ":money_transaction_api")))
    implementation(project(mapOf("path" to ":reports")))
    implementation(project(mapOf("path" to ":wallet")))
    implementation(project(mapOf("path" to ":currencies")))
    implementation(project(mapOf("path" to ":network_api")))
    implementation(project(mapOf("path" to ":network_impl")))
    implementation(project(mapOf("path" to ":reports_api")))

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)

    implementation(libs.dagger)
    kapt(libs.dagger.compiler.kapt)

}