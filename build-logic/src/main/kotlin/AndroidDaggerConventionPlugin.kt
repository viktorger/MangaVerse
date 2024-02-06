import com.viktorger.mangaverse.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidDaggerConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("kotlin-kapt")
            }

            dependencies {
                "implementation"(libs.findLibrary("dagger").get())
                "kapt"(libs.findLibrary("dagger.compiler").get())
            }
        }
    }
}