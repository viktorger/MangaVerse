package com.viktorger.mangaverse.manga_description.navigation

import android.os.Bundle
import androidx.navigation.NavDirections

interface MangaDescriptionNavigation {
    fun getMangaUrl(arguments: Bundle): String

    fun navigateToChapter(chapterUrl: String, navigate: (action: NavDirections) -> Unit)
}