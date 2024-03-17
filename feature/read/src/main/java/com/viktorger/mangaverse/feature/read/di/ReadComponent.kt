package com.viktorger.mangaverse.feature.read.di

import com.viktorger.mangaverse.feature.read.ReadFragment
import dagger.Subcomponent

@Subcomponent
interface ReadComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ReadComponent
    }

    fun inject(readFragment: ReadFragment)

}