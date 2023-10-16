import org.gradle.api.JavaVersion

object Constants {
    val JAVA_VERSION = JavaVersion.VERSION_17
    val JVM_TARGET = JAVA_VERSION.toString()
}