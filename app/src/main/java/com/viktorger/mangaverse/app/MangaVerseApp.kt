package com.viktorger.mangaverse.app

import android.app.Application
import com.viktorger.mangaverse.di.AppComponent
import com.viktorger.mangaverse.di.DaggerAppComponent
import com.viktorger.mangaverse.feature.home.di.HomeComponent
import com.viktorger.mangaverse.feature.home.di.HomeComponentProvider
import com.viktorger.mangaverse.feature.read.di.ReadComponent
import com.viktorger.mangaverse.feature.read.di.ReadComponentProvider
import com.viktorger.mangaverse.manga_description.di.MangaDescriptionComponent
import com.viktorger.mangaverse.manga_description.di.MangaDescriptionComponentProvider

class MangaVerseApp : Application(), HomeComponentProvider, MangaDescriptionComponentProvider,
    ReadComponentProvider {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.create()
    }

    override fun provideHomeComponent(): HomeComponent =
        appComponent.homeComponent().create()

    override fun provideMangaDescriptionComponent(): MangaDescriptionComponent =
        appComponent.mangaDescriptionComponent().create()

    override fun provideReadComponent(): ReadComponent =
        appComponent.readComponent().create()
}