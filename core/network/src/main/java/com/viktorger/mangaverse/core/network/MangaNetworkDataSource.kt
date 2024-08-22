package com.viktorger.mangaverse.core.network

import com.viktorger.mangaverse.core.model.MangaChapter
import com.viktorger.mangaverse.core.model.MangaDetails
import com.viktorger.mangaverse.core.network.model.MangaDescription
import com.viktorger.mangaverse.core.model.MangaShortcut

interface MangaNetworkDataSource {
    suspend fun getShortcutsPage(page: Int): List<MangaShortcut>
    suspend fun getDetails(mangaUrl: String): MangaDetails
    suspend fun getChapter(mangaUrl: String): MangaChapter
}