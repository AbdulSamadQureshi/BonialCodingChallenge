plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.rabbah.data"
    compileSdk = 36

    defaultConfig {
        minSdk = 24
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
    implementation(project(":network"))
    implementation(project(":domain"))
    implementation(project(":core"))
    implementation(libs.koin.core)
    implementation(libs.androidx.core.ktx)
    implementation(libs.gson)
    testImplementation(libs.junit)
}
