package com.viktorger.mangaverse.core.network

import com.viktorger.mangaverse.core.model.MangaShortcut

interface MangaNetworkDataSource {
    suspend fun getShortcutsPage(page: Int): List<MangaShortcut>
}