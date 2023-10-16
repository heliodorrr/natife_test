plugins {
    id("local.feature")

}

android {
    namespace = "com.helio.ui"

}

dependencies {
    implementation(project(":api"))

    implementation(libs.bundles.ktx)

    implementation(libs.bundles.adapterDelegatesBundle)

    implementation(libs.glide)
    implementation(libs.paging)


    implementation(libs.appCompat)
    implementation(libs.material)
    implementation(libs.navFragment)
    implementation(libs.navUi)
}