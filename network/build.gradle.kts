import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.bonial.network"
    compileSdk = 37

    defaultConfig {
        minSdk = 24
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    kotlin {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)
        }
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":domain"))

    // Retrofit — exposed as `api` because consumers (data, app) define API service
    // interfaces and DI wiring against these types. Keeping them on the public surface
    // keeps :network the single source of truth for the networking stack rather than
    // forcing every consumer to re-declare the dependency.
    api(libs.retrofit.core)
    api(libs.retrofit.gson)

    // OkHttp logging is a private implementation detail of RetrofitClient — no consumer
    // needs the type, so it stays off the module's public surface.
    implementation(libs.okhttp.logging)


    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation(libs.gson)

    testImplementation(libs.junit)
    testImplementation(libs.truth)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockwebserver)
}
