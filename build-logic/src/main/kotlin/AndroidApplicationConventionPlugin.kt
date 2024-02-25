import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.viktorger.mangaverse.libs
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("mangaverse.android.dagger")
            }

            extensions.configure<BaseAppModuleExtension> {
                compileSdk = 34

                defaultConfig {
                    applicationId = "com.viktorger.mangaverse"
                    minSdk = 24
                    targetSdk = 34
                    versionCode = 1
                    versionName = "1.0"

                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }

                buildTypes {
                    release {
                        isMinifyEnabled = false
                        proguardFiles(
                            getDefaultProguardFile("proguard-android-optimize.txt"),
                            "proguard-rules.pro"
                        )
                    }
                }
                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_1_8
                    targetCompatibility = JavaVersion.VERSION_1_8
                }
                tasks.withType<KotlinCompile>().configureEach {
                    kotlinOptions {
                        jvmTarget = JavaVersion.VERSION_1_8.toString()
                    }
                }

                buildFeatures {
                    viewBinding = true
                }
            }

            dependencies {
                "implementation"(project(":feature:home"))
                "implementation"(project(":feature:library"))
                "implementation"(project(":core:data"))
                "implementation"(project(":core:network"))
                "implementation"(project(":core:designsystem"))
            }
        }
    }
}