package com.viktorger.mangaverse.core.model

data class MangaChapter(
    val chapterTitle: String,
    var pagesUrls: List<String>,
    val prevPageUrl: String?,
    val nextPageUrl: String?
)
