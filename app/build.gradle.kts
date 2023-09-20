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

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.1")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.1")
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
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("com.google.dagger:dagger:2.47")
    kapt("com.google.dagger:dagger-compiler:2.47")

}