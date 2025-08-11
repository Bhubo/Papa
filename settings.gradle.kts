// settings.gradle.kts

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal() // For foojay-resolver-convention and other plugins
    }
    plugins {
        // Allows Gradle to automatically download the right JDK from Foojay
        id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

// Project name
rootProject.name = "papa-android"

// List your modules here
// include(":app-phone")
// include(":app-wear")
// include(":app-tv")
// include(":app-car")
// include(":core:design")
// include(":core:ui")
// include(":core:equilibrium")
