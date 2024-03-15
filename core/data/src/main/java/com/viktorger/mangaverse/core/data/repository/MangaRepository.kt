package com.viktorger.mangaverse.core.data.repository

import com.viktorger.mangaverse.core.model.MangaChapter
import com.viktorger.mangaverse.core.model.MangaDetails
import com.viktorger.mangaverse.core.model.MangaShortcut
import com.viktorger.mangaverse.core.model.ResultModel

interface MangaRepository {
    suspend fun getShortcuts(page: Int): List<MangaShortcut>
    suspend fun getDetails(mangaUrl: String): ResultModel<MangaDetails>
    suspend fun getChapters(mangaUrl: String): ResultModel<List<MangaChapter>>
}