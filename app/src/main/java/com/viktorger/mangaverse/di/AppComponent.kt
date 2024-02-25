package com.viktorger.mangaverse.di

import com.viktorger.mangaverse.core.data.di.DataModule
import com.viktorger.mangaverse.core.network.di.NetworkModule
import com.viktorger.mangaverse.feature.home.di.HomeComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    SubcomponentsModule::class, NetworkModule::class, DataModule::class
])
interface AppComponent {

    fun homeComponent(): HomeComponent.Factory

}