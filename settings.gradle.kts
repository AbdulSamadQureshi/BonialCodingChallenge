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
        maven { url = uri("https://jitpack.io") }
        maven { url = uri("https://ramani.jfrog.io/artifactory/maplibre-android") }
    }
}

rootProject.name = "Close Loop Wallet"
include(":app")
include(":data")
include(":domain")
include(":network")
include(":core")
