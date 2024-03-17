package com.viktorger.mangaverse.feature.read

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.viktorger.mangaverse.core.data.repository.MangaRepository
import javax.inject.Inject

class ReadViewModelFactory @Inject constructor(
    private val mangaRepository: MangaRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ReadViewModel(mangaRepository) as T
    }
}