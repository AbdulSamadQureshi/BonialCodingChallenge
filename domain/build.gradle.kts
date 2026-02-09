plugins {
    id("org.jetbrains.kotlin.jvm")
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation(libs.koin.core)
    implementation(libs.ktor.serialization.kotlinx.json)
    testImplementation(libs.junit)
}
