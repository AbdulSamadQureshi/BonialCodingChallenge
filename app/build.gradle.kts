import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.bonial.brochure"
    compileSdk {
        version = release(36)
    }

    signingConfigs {
        create("release") {
            val keystorePropertiesFile = rootProject.file("keystore.release.properties")
            if (keystorePropertiesFile.exists()) {
                val keystoreProperties = Properties()
                keystoreProperties.load(FileInputStream(keystorePropertiesFile))
                storeFile = file(keystoreProperties.getProperty("storeFile"))
                storePassword = keystoreProperties.getProperty("storePassword")
                keyAlias = keystoreProperties.getProperty("keyAlias")
                keyPassword = keystoreProperties.getProperty("keyPassword")
            }
        }
        getByName("debug") {
            val keystorePropertiesFile = rootProject.file("keystore.debug.properties")
            if (keystorePropertiesFile.exists()) {
                val keystoreProperties = Properties()
                keystoreProperties.load(FileInputStream(keystorePropertiesFile))
                storeFile = file(keystoreProperties.getProperty("storeFile"))
                storePassword = keystoreProperties.getProperty("storePassword")
                keyAlias = keystoreProperties.getProperty("keyAlias")
                keyPassword = keystoreProperties.getProperty("keyPassword")
            }
        }
    }

    defaultConfig {
        applicationId = "com.bonial.brochure"
        minSdk = 25
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            signingConfig = signingConfigs.getByName("release")
            isDebuggable = false
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            val releaseProperties = Properties()
            val releasePropertiesFile = rootProject.file("release.properties")
            if (releasePropertiesFile.exists()) {
                releaseProperties.load(FileInputStream(releasePropertiesFile))
                buildConfigField("String", "BASE_URL", "\"${releaseProperties.getProperty("BASE_URL")}\"")
                buildConfigField("String", "ENVIRONMENT", "\"${releaseProperties.getProperty("ENVIRONMENT")}\"")
            }
        }

        getByName("debug") {
            signingConfig = signingConfigs.getByName("debug")
            isDebuggable = true
            val debugProperties = Properties()
            val debugPropertiesFile = rootProject.file("staging.properties")
            if (debugPropertiesFile.exists()) {
                debugProperties.load(FileInputStream(debugPropertiesFile))
                buildConfigField("String", "BASE_URL", "\"${debugProperties.getProperty("BASE_URL")}\"")
            }
        }

        val qa by creating {
            initWith(getByName("debug"))
            isDebuggable = true
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            applicationIdSuffix = ".qa"
            versionNameSuffix = "-qa"

            val qaProperties = Properties()
            val qaPropertiesFile = rootProject.file("qa.properties")
            if (qaPropertiesFile.exists()) {
                qaProperties.load(FileInputStream(qaPropertiesFile))

                buildConfigField("String", "BASE_URL", "\"${qaProperties.getProperty("BASE_URL")}\"")
                buildConfigField("String", "ENVIRONMENT", "\"${qaProperties.getProperty("ENVIRONMENT")}\"")
            }
        }

        val staging by creating {
            initWith(getByName("debug"))
            isDebuggable = true
            isMinifyEnabled = false
            isShrinkResources = false
            applicationIdSuffix = ".staging"
            versionNameSuffix = "-staging"

            val stagingProperties = Properties()
            val stagingPropertiesFile = rootProject.file("staging.properties")
            if (stagingPropertiesFile.exists()) {
                stagingProperties.load(FileInputStream(stagingPropertiesFile))
                buildConfigField("String", "BASE_URL", "\"${stagingProperties.getProperty("BASE_URL")}\"")
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    implementation(project(":network"))
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":core"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.coil)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.compose.material3.adaptive.navigation.suite)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}
