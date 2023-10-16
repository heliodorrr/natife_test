plugins {
    id("local.lib")
    alias(libs.plugins.kotlinxSerialization)
}

android {
    namespace = "com.helio.impl"
}

dependencies {
    api(project(":api"))
    implementation(libs.loggingInterceptor)
    implementation(libs.retrofit)
    implementation(libs.retrofitKotlinxSerializationConverter)
    implementation(libs.kotlinxSerializationJson)

    implementation(libs.coreKtx)
    implementation(libs.appCompat)
    implementation(libs.material)
}