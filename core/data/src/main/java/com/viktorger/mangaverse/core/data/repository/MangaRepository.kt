package com.viktorger.mangaverse.core.data.repository

import com.viktorger.mangaverse.core.model.MangaChapter
import com.viktorger.mangaverse.core.model.MangaDetails
import com.viktorger.mangaverse.core.model.MangaShortcut

interface MangaRepository {
    suspend fun getShortcuts(page: Int): List<MangaShortcut>
    suspend fun getDescription(mangaUrl: String): MangaDetails
    suspend fun getChapter(mangaUrl: String) : MangaChapter
}