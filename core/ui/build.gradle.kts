plugins {

    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.surreallabs.papa.ui"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdkPhone.get().toInt()
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures { compose = true }
    
}

dependencies {
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.material3)
    implementation(libs.compose.runtime)
    implementation(libs.compose.tooling.preview)
    debugImplementation(libs.compose.tooling)

    implementation(project(":core:design"))
}
