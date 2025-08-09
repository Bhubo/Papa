plugins {

    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.surreallabs.papa.tv"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.surreallabs.papa.tv"
        minSdk = libs.versions.minSdkTv.get().toInt()
        targetSdk = libs.versions.compileSdk.get().toInt()
        versionCode = 1
        versionName = "0.1.0"
    }

    buildFeatures { compose = true }
    
}

dependencies {
    implementation(platform(libs.compose.bom))
    implementation(libs.androidx.tv.foundation)
    implementation(libs.androidx.tv.material3)
    implementation(libs.activity.compose)

    implementation(project(":core:design"))
    implementation(project(":core:ui"))
    implementation(project(":core:equilibrium"))
}
