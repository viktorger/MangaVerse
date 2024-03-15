package com.viktorger.mangaverse.di

import com.viktorger.mangaverse.feature.home.di.HomeComponent
import com.viktorger.mangaverse.manga_description.di.MangaDescriptionComponent
import dagger.Module

@Module(subcomponents = [HomeComponent::class, MangaDescriptionComponent::class])
class SubcomponentsModule