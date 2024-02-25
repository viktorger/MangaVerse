plugins {
    alias(libs.plugins.mangaverse.android.library)
    alias(libs.plugins.mangaverse.android.dagger)
}

android {
    namespace = "com.viktorger.mangaverse.core.network"
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:common"))
    implementation(libs.jsoup)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}