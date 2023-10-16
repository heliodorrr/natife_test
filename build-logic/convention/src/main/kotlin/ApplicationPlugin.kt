import com.android.build.api.dsl.ApplicationExtension
import ext.configureBase
import ext.libs
import ext.pluginOrThrow
import org.gradle.api.Plugin
import org.gradle.api.Project

class ApplicationPlugin: Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        pluginManager.apply(libs.pluginOrThrow("application").pluginId)
        configureBase<ApplicationExtension> {

        }
    }
}