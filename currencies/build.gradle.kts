plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    namespace = "ru.niku.currencies"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    buildFeatures{
        viewBinding = true
    }
}

dependencies {

    //implementation("androidx.core:core-ktx:1.12.0")
    implementation(libs.androidx.core.ktx)
    //implementation("androidx.appcompat:appcompat:1.6.1")
    implementation(libs.androidx.appcompat)
    //implementation("com.google.android.material:material:1.9.0")
    implementation(libs.material)
    //testImplementation("junit:junit:4.13.2")
    testImplementation(libs.junit)
    //androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation(libs.androidx.test.ext.junit)
    //androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(libs.androidx.test.espresso.core)

    //implementation("androidx.fragment:fragment-ktx:1.6.1")
    implementation(libs.androidx.fragment.ktx)

    //implementation("com.google.dagger:dagger:2.47")
    implementation(libs.dagger)
    //kapt("com.google.dagger:dagger-compiler:2.47")
    kapt(libs.dagger.compiler.kapt)

    //retrofit
    //implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation(libs.retrofit2)
    //implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation(libs.retrofit2.converter.gson)
    //implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    implementation(libs.swiperefreshlayout)

    // project modules
    api(project(mapOf("path" to ":core_api")))

}