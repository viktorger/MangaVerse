package com.viktorger.mangaverse.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.viktorger.mangaverse.core.data.repository.MangaRepository
import javax.inject.Inject

class HomeViewModelFactory @Inject constructor(
    private val mangaRepository: MangaRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(mangaRepository) as T
    }
}