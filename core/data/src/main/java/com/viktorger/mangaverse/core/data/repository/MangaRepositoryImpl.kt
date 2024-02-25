package com.viktorger.mangaverse.core.data.repository

import com.viktorger.mangaverse.core.model.MangaShortcut
import com.viktorger.mangaverse.core.network.MangaNetworkDataSource
import javax.inject.Inject

class MangaRepositoryImpl @Inject constructor(
    private val mangaNetworkDataSource: MangaNetworkDataSource
) : MangaRepository {
    override suspend fun getShortcuts(page: Int): List<MangaShortcut> = mangaNetworkDataSource
        .getShortcutsPage(page)
}