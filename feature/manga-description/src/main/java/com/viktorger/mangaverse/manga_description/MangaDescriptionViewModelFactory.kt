package com.viktorger.mangaverse.manga_description

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.viktorger.mangaverse.core.data.repository.MangaRepository
import javax.inject.Inject

class MangaDescriptionViewModelFactory @Inject constructor(
    private val mangaRepository: MangaRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MangaDescriptionViewModel(mangaRepository) as T
    }
}