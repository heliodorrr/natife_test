import com.android.build.api.dsl.LibraryExtension
import ext.configureBase
import ext.libs
import ext.pluginOrThrow
import org.gradle.api.Plugin
import org.gradle.api.Project

class LibraryPlugin: Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply(libs.pluginOrThrow("library").pluginId)
        configureBase<LibraryExtension> {

        }
    }
}