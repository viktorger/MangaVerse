package com.viktorger.mangaverse.manga_details

sealed interface MangaDetailsCommand {
    data class LoadDetails(val mangaUrl: String) : MangaDetailsCommand
}