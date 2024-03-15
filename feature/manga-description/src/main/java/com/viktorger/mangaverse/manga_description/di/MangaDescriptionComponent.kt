package com.viktorger.mangaverse.manga_description.di

import com.viktorger.mangaverse.manga_description.MangaDescriptionFragment
import dagger.Subcomponent

@Subcomponent
interface MangaDescriptionComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): MangaDescriptionComponent
    }

    fun inject(mangaDescriptionFragment: MangaDescriptionFragment)
}