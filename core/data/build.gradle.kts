@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.mangaverse.android.library)
    alias(libs.plugins.mangaverse.android.dagger)
}

android {
    namespace = "com.viktorger.mangaverse.core.data"
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:network"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}