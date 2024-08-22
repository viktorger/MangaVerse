package com.viktorger.mangaverse.manga_details

import com.viktorger.mangaverse.core.model.LceState
import com.viktorger.mangaverse.core.model.MangaDetails

data class MangaDetailsState(
    val mangaDetails: LceState<MangaDetails>
)