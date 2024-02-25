package com.viktorger.mangaverse.core.data.repository

import com.viktorger.mangaverse.core.model.MangaShortcut

interface MangaRepository {
    suspend fun getShortcuts(page: Int): List<MangaShortcut>
}