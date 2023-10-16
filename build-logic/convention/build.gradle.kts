
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

val javaVersion = JavaVersion.VERSION_17


java {
    sourceCompatibility = javaVersion
    targetCompatibility = javaVersion
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "$javaVersion"
    }
}

dependencies {
    implementation(libs.agp)
    implementation(libs.kgp)
    implementation(libs.ksp)
}

gradlePlugin {
    plugins {
        register("localAppPlugin") {
            id = "local.app"
            implementationClass = "ApplicationPlugin"
        }
        register("localLibPlugin") {
            id = "local.lib"
            implementationClass = "LibraryPlugin"
        }
        register("localFeaturePlugin") {
            id = "local.feature"
            implementationClass = "FeaturePlugin"
        }
    }
}
