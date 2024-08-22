package com.viktorger.mangaverse.manga_details.navigation

import android.os.Bundle
import androidx.navigation.NavDirections

interface MangaDetailsNavigation {
    fun getMangaUrl(arguments: Bundle): String

    fun navigateToChapter(chapterUrl: String, navigate: (action: NavDirections) -> Unit)
}