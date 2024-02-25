package com.viktorger.mangaverse.feature.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.viktorger.mangaverse.core.data.repository.MangaRepository
import com.viktorger.mangaverse.core.model.MangaShortcut
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val mangaRepository: MangaRepository
) : ViewModel() {

    private val _shortcutListLiveData = MutableLiveData(listOf<MangaShortcut>())
    val shortcutListLiveData: LiveData<List<MangaShortcut>> = _shortcutListLiveData

    fun doSmth() {
        viewModelScope.launch (Dispatchers.Default) {
            _shortcutListLiveData.postValue(mangaRepository.getShortcuts(1))
        }
    }
}