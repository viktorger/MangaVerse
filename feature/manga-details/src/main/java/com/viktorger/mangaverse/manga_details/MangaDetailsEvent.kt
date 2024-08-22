package com.viktorger.mangaverse.manga_details

import com.viktorger.mangaverse.core.model.MangaDetails

sealed interface MangaDetailsEvent {
    sealed interface Ui : MangaDetailsEvent {
        data class LoadDetails(val mangaUrl: String) : Ui
    }

    sealed interface Internal : MangaDetailsEvent {
        data class DetailsLoaded(val mangaDetails: MangaDetails) : Internal
        data class DetailsLoadFail(val throwable: Throwable) : Internal
    }
}