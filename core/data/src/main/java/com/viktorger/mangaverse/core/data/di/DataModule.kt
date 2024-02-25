package com.viktorger.mangaverse.core.data.di

import com.viktorger.mangaverse.core.data.repository.MangaRepository
import com.viktorger.mangaverse.core.data.repository.MangaRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    abstract fun provideMangaRepository(mangaRepositoryImpl: MangaRepositoryImpl): MangaRepository

}