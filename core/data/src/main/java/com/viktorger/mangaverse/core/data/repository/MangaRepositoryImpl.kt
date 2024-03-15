package com.viktorger.mangaverse.core.data.repository

import com.viktorger.mangaverse.core.model.MangaChapter
import com.viktorger.mangaverse.core.model.MangaDetails
import com.viktorger.mangaverse.core.model.MangaShortcut
import com.viktorger.mangaverse.core.model.ResultModel
import com.viktorger.mangaverse.core.network.MangaNetworkDataSource
import javax.inject.Inject

class MangaRepositoryImpl @Inject constructor(
    private val mangaNetworkDataSource: MangaNetworkDataSource
) : MangaRepository {
    override suspend fun getShortcuts(page: Int): List<MangaShortcut> = mangaNetworkDataSource
        .getShortcutsPage(page)

    override suspend fun getDetails(mangaUrl: String): ResultModel<MangaDetails> = mangaNetworkDataSource
        .getDetails(mangaUrl)

    override suspend fun getChapters(mangaUrl: String): ResultModel<List<MangaChapter>> = mangaNetworkDataSource
        .getChapters(mangaUrl)
}