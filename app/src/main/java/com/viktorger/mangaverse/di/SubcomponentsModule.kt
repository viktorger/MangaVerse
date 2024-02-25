package com.viktorger.mangaverse.di

import com.viktorger.mangaverse.feature.home.di.HomeComponent
import dagger.Module

@Module(subcomponents = [HomeComponent::class])
class SubcomponentsModule