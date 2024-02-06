import org.gradle.kotlin.dsl.gradlePlugin

plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.android.tools.common)
}

gradlePlugin {
    plugins {
        register("androidDagger") {
            id = "mangaverse.android.dagger"
            implementationClass = "AndroidDaggerConventionPlugin"
        }

        register("androidLibrary") {
            id = "mangaverse.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        register("androidFeature") {
            id = "mangaverse.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }

        register("androidApplication") {
            id = "mangaverse.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
    }
}