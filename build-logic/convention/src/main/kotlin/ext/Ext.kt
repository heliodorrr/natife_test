package ext

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.plugin.use.PluginDependency
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

val Project.libs get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

fun VersionCatalog.libOrThrow(name: String): MinimalExternalModuleDependency {
    return findLibrary(name).orElseThrow().get()
}

fun VersionCatalog.pluginOrThrow(name: String): PluginDependency {
    return findPlugin(name).orElseThrow().get()
}

inline fun <reified T: CommonExtension<*, *, *, *, *>> Project.configureBase(
    crossinline block: T.()->Unit
) {

    with(pluginManager) {
        apply(libs.pluginOrThrow("ksp").pluginId)
        apply(libs.pluginOrThrow("kotlinAndroid").pluginId)
    }

    extensions.configure<T> {
        compileSdk = 34
        defaultConfig {
            minSdk = 24
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        compileOptions {
            sourceCompatibility = Constants.JAVA_VERSION
            targetCompatibility = Constants.JAVA_VERSION
            isCoreLibraryDesugaringEnabled = true
        }

        buildTypes {
            getByName("release") {
                isMinifyEnabled = false
            }
        }

        (this as org.gradle.api.plugins.ExtensionAware)
            .extensions.configure<KotlinJvmOptions>("kotlinOptions") {
                jvmTarget = Constants.JVM_TARGET
            }

        block()
    }

    dependencies {
        add("coreLibraryDesugaring", libs.libOrThrow("desugaring"))

        add("implementation", libs.libOrThrow("dagger2"))
        add("ksp", libs.libOrThrow("dagger2Compiler"))

    }

}