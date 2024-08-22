package com.viktorger.mangaverse.manga_details

import com.viktorger.mangaverse.core.model.LceState
import money.vivid.elmslie.core.store.ScreenReducer

class MangaDetailsReducer :
    ScreenReducer<MangaDetailsEvent,
            MangaDetailsEvent.Ui,
            MangaDetailsEvent.Internal,
            MangaDetailsState,
            MangaDetailsEffect,
            MangaDetailsCommand>(
                MangaDetailsEvent.Ui::class, MangaDetailsEvent.Internal::class
            ) {

    override fun Result.internal(event: MangaDetailsEvent.Internal): Any = when (event) {
        is MangaDetailsEvent.Internal.DetailsLoadFail -> Unit
        is MangaDetailsEvent.Internal.DetailsLoaded -> state {
            copy(
                mangaDetails = LceState.Content(event.mangaDetails)
            )
        }
    }

    override fun Result.ui(event: MangaDetailsEvent.Ui): Any = when (event) {
        is MangaDetailsEvent.Ui.LoadDetails -> commands {
            +MangaDetailsCommand.LoadDetails(event.mangaUrl)
        }
    }
}