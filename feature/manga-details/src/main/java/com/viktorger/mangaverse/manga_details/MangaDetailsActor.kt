package com.viktorger.mangaverse.manga_details

import com.viktorger.mangaverse.core.data.repository.MangaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import money.vivid.elmslie.core.store.Actor
import javax.inject.Inject

class MangaDetailsActor @Inject constructor(
    private val mangaRepository: MangaRepository
) : Actor<MangaDetailsCommand, MangaDetailsEvent>() {

    override fun execute(command: MangaDetailsCommand): Flow<MangaDetailsEvent> = when (command) {
        is MangaDetailsCommand.LoadDetails -> loadDetails(command.mangaUrl)
    }

    private fun loadDetails(
        mangaUrl: String
    ): Flow<MangaDetailsEvent> = flow<MangaDetailsEvent> {
        val mangaDescription = mangaRepository.getDescription(mangaUrl)
        emit(MangaDetailsEvent.Internal.DetailsLoaded(mangaDescription))
    }.catch {
        emit(MangaDetailsEvent.Internal.DetailsLoadFail(it))
    }



}