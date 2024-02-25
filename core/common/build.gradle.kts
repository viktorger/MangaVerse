plugins {
    alias(libs.plugins.mangaverse.android.library)
}

android {
    namespace = "com.viktorger.mangaverse.common"
}

dependencies {

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}