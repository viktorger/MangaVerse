package com.viktorger.mangaverse.core.network

import com.viktorger.mangaverse.core.model.MangaChapter
import com.viktorger.mangaverse.core.model.MangaDetails
import com.viktorger.mangaverse.core.model.MangaShortcut
import com.viktorger.mangaverse.core.model.ResultModel

interface MangaNetworkDataSource {
    suspend fun getShortcutsPage(page: Int): List<MangaShortcut>
    suspend fun getDetails(mangaUrl: String): ResultModel<MangaDetails>
    suspend fun getChapters(mangaUrl: String): ResultModel<List<MangaChapter>>
}