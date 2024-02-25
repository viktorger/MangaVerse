package com.viktorger.mangaverse.feature.home.di

import com.viktorger.mangaverse.feature.home.HomeFragment
import dagger.Subcomponent

@Subcomponent
interface HomeComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): HomeComponent
    }

    fun inject(homeFragment: HomeFragment)
}