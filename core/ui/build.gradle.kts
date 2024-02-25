plugins {
    alias(libs.plugins.mangaverse.android.library)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.viktorger.mangaverse.core.ui"

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":core:designsystem"))
    implementation(project(":core:model"))

    implementation(libs.glide)
    ksp(libs.glide.ksp)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}