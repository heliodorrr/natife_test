plugins {
    id("local.lib")

}

android {
    namespace = "com.helio.api"
}

dependencies {
    implementation(libs.coreKtx)
    implementation(libs.appCompat)
    implementation(libs.material)

}