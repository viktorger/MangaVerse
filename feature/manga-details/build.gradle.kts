@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.mangaverse.android.feature)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "com.viktorger.mangaverse.manga_details"
}

dependencies {
    implementation(libs.glide)
    implementation(libs.elmslie.core)
    implementation(libs.elmslie.android)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}