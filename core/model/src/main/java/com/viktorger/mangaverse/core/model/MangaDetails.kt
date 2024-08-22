package com.viktorger.mangaverse.core.model

data class MangaDetails(
    val title: String,
    val genres: String,
    val description: String,
    val imageUrl: String,
    val mangaChaptersShortcuts: List<MangaChapterShortcut>
)
