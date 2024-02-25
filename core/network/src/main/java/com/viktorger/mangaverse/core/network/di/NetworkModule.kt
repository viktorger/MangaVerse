package com.viktorger.mangaverse.core.network.di

import com.viktorger.mangaverse.core.network.MangaNetworkDataSource
import com.viktorger.mangaverse.core.network.jsoup.JsoupMangaNetworkDataSource
import dagger.Binds
import dagger.Module

@Module
abstract class NetworkModule {

    @Binds
    abstract fun provideMangaNetworkDataSource(
        jsoupMangaNetworkDataSource: JsoupMangaNetworkDataSource
    ): MangaNetworkDataSource
}