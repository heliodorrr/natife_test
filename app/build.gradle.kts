plugins {
    id("local.app")
}

android {
    namespace = "com.helio.natifetest"

    defaultConfig {
        applicationId = "com.helio.natifetest"
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    implementation(libs.appCompat)
    implementation(libs.retrofit)
    implementation(libs.glide)
    implementation(libs.kotlinxSerializationJson)
    implementation(libs.bundles.adapterDelegatesBundle)
    implementation(libs.bundles.ktx)
    implementation(libs.bundles.navigation)

    implementation(project(":api"))
    implementation(project(":impl"))
    implementation(project(":ui"))

}