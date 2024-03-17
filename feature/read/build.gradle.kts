plugins {
    alias(libs.plugins.mangaverse.android.feature)
}

android {
    namespace = "com.viktorger.mangaverse.feature.read"
}

dependencies {
    implementation(libs.glide)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}