package com.viktorger.mangaverse.manga_details.di

import com.viktorger.mangaverse.manga_details.MangaDetailsFragment
import dagger.Subcomponent

@Subcomponent
interface MangaDescriptionComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): MangaDescriptionComponent
    }

    fun inject(mangaDetailsFragment: MangaDetailsFragment)
}