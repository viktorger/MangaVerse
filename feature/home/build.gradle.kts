@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed

plugins {
    alias(libs.plugins.mangaverse.android.feature)
}

android {
    namespace = "com.viktorger.mangaverse.feature.home"
}

dependencies {
    implementation(project(":core:ui"))

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}