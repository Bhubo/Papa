plugins {

    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.surreallabs.papa.wear"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.surreallabs.papa.wear"
        minSdk = libs.versions.minSdkWear.get().toInt()
        targetSdk = libs.versions.compileSdk.get().toInt()
        versionCode = 1
        versionName = "0.1.0"
    }

    buildFeatures { compose = true }
    
}

dependencies {
    implementation(platform(libs.compose.bom))
    implementation(libs.wear.compose.material)
    implementation(libs.wear.compose.foundation)
    implementation(libs.activity.compose)

    implementation(project(":core:design"))
    implementation(project(":core:ui"))
    implementation(project(":core:equilibrium"))
}
