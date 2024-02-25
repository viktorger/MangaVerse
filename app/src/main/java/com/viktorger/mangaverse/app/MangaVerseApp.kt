package com.viktorger.mangaverse.app

import android.app.Application
import com.viktorger.mangaverse.di.AppComponent
import com.viktorger.mangaverse.di.DaggerAppComponent
import com.viktorger.mangaverse.feature.home.di.HomeComponent
import com.viktorger.mangaverse.feature.home.di.HomeComponentProvider

class MangaVerseApp : Application(), HomeComponentProvider {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.create()
    }

    override fun provideHomeComponent(): HomeComponent =
        appComponent.homeComponent().create()

}