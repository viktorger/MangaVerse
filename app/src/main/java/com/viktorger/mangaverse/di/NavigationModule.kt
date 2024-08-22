package com.viktorger.mangaverse.di

import android.os.Bundle
import androidx.navigation.NavDirections
import com.viktorger.mangaverse.feature.home.HomeFragmentDirections
import com.viktorger.mangaverse.feature.home.navigation.HomeNavigation
import com.viktorger.mangaverse.feature.read.ReadFragmentArgs
import com.viktorger.mangaverse.feature.read.navigation.ReadNavigation
import com.viktorger.mangaverse.manga_details.MangaDetailsFragmentArgs
import com.viktorger.mangaverse.manga_details.MangaDetailsFragmentDirections
import com.viktorger.mangaverse.manga_details.navigation.MangaDetailsNavigation
import dagger.Module
import dagger.Provides

@Module
class NavigationModule {

    @Provides
    fun provideHomeNavigation(): HomeNavigation = object : HomeNavigation {
        override fun navigateToDescription(
            mangaUrl: String,
            navigate: (action: NavDirections) -> Unit
        ) {
            val action =
                HomeFragmentDirections.actionHomeFragmentToMangaDescriptionFragment(mangaUrl)
            navigate(action)
        }
    }

    @Provides
    fun provideMangaDescriptionNavigation(): MangaDetailsNavigation = object : MangaDetailsNavigation {
        override fun getMangaUrl(arguments: Bundle): String =
            MangaDetailsFragmentArgs.fromBundle(arguments).mangaUrl

        override fun navigateToChapter(
            chapterUrl: String,
            navigate: (action: NavDirections) -> Unit
        ) {
            val action =
                MangaDetailsFragmentDirections.actionMangaDescriptionFragmentToReadFragment(chapterUrl)

            navigate(action)
        }
    }

    @Provides
    fun provideReadNavigation(): ReadNavigation = object : ReadNavigation {
        override fun getChapterUrl(arguments: Bundle): String =
            ReadFragmentArgs.fromBundle(arguments).chapterUrl
    }
}