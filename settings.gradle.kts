pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "papa-android"

include(":app-phone")
include(":app-wear")
include(":app-tv")
include(":app-car")
include(":core:design")
include(":core:ui")
include(":core:equilibrium")
