package com.viktorger.mangaverse.manga_details

import com.viktorger.mangaverse.core.model.LceState
import money.vivid.elmslie.core.store.ElmStore
import money.vivid.elmslie.core.store.Store
import javax.inject.Inject

class MangaDetailsStoreFactory @Inject constructor(
    private val mangaDetailsActor: MangaDetailsActor
) {
    fun create(): Store<MangaDetailsEvent, MangaDetailsEffect, MangaDetailsState> = ElmStore(
        initialState = MangaDetailsState(
            mangaDetails = LceState.Loading
        ),
        reducer = MangaDetailsReducer(),
        actor = mangaDetailsActor
    )
}