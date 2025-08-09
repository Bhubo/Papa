plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.surreallabs.papa.equilibrium"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdkPhone.get().toInt()
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures { compose = false }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.datastore)
}
