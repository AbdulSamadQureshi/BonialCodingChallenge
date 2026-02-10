plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.bonial.network"
    compileSdk = 36

    defaultConfig {
        minSdk = 24
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlin {
        jvmToolchain(17)
    }
}

dependencies {
    implementation(project(":core"))
    
    // Retrofit
    api(libs.retrofit.core)
    api(libs.retrofit.kotlin.serialization)
    api(libs.okhttp.logging)
    
    implementation(libs.koin.core)
    implementation(libs.kotlinx.serialization.json)
}
