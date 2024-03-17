plugins {
    alias(libs.plugins.mangaverse.android.library)
}

android {
    namespace = "com.viktorger.mangaverse.common"
}

dependencies {

    implementation(libs.androidx.fragment.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}